package chatApp;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import javax.swing.*;
import javax.swing.text.DefaultCaret;


public class handleCalls implements ActionListener, Runnable {

	//private final String HOST = "127.0.0.1";
	//private final int PORT = 12345;
	private final JFrame f = new JFrame();
	private final JTextField tf = new JTextField(25);
	private final JTextArea ta = new JTextArea(18, 25);
	private final JButton send = new JButton("Send");
	private volatile PrintWriter out;
	private Scanner in;
	private Thread thread;
	private String kind;
	private String contact = null;
	private Socket openSock = null;
	public static JLabel label = new JLabel();
	Font font = new Font("Arial", Font.HANGING_BASELINE, 13);

	@SuppressWarnings("deprecation")
	public handleCalls(String kind, Socket openSock) {
		this.openSock = openSock;
		this.kind = kind;
		this.contact = (String) Launcher.contactList.getSelectedValue();

		f.setTitle("Call from " + contact);
		f.getRootPane().setDefaultButton(send);
		f.add(tf, BorderLayout.SOUTH);
		f.add(label,BorderLayout.WEST);
		f.add(new JScrollPane(ta), BorderLayout.PAGE_START);
		f.add(send, BorderLayout.LINE_END);
		f.setLocation(500, 300);
		f.pack();
		send.addActionListener(this);
		ta.setLineWrap(true);
		ta.setWrapStyleWord(true);
		ta.disable();
		ta.setDisabledTextColor(new Color(125, 0, 0));
		ta.setFont(font);
		DefaultCaret caret = (DefaultCaret) ta.getCaret();
		caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
		//display(activity + HOST + " on port " + PORT);
		thread = new Thread(this, kind.toString());
	}

	public void start() {
		// f.setVisible(true);
		thread.start();
	}

	// @Override
	public void actionPerformed(ActionEvent ae) {
		String s = tf.getText();
		if (out != null) {
			out.println(s);
		}
		display(s);
		tf.setText("");
	}

	// @Override
	public void run() {
		try {
			Socket socket;
			
			if (Objects.equals(kind, "Client")) {
				String[] param= AddressBook.getParam(contact);
				f.setVisible(true);
				f.setTitle(contact);
				f.setSize(500, 400);
				//ClientClass myClass = new ClientClass(param[2]);
				//myClass.start();
				socket = new Socket(param[0], Integer.parseInt(param[1]));
				in = new Scanner(socket.getInputStream());
				out = new PrintWriter(socket.getOutputStream(), true);

				out.write(Launcher.getUId());
				send.doClick();
				out.write(".249");
				send.doClick();
				display("Waiting for response...");
				String response = in.nextLine();
				if (Objects.equals(response, "1") && response!=null)
					display("Connected");
				else
					display("Connection Refused");
				while (true) {
					display(contact + ": " + in.nextLine());
				}
			} else {
				// set up
				socket = openSock;
				//if(callersCamIP!=null)
				//ClientClass myClass = new ClientClass(callersCamIP);
				//myClass.start();
				//else label.setText("Video Stream Not Available");
				in = new Scanner(socket.getInputStream());
				out = new PrintWriter(socket.getOutputStream(), true);
				String caller = in.nextLine();
				Integer input = null;
				// accept call

				input = JOptionPane.showConfirmDialog(null, "Incomming call from: " + caller);
				if (input == 0 && input!=null) {
					f.setVisible(true);
					f.setTitle(caller);
					display("Connected to " + caller);
					out.write('1');
					send.doClick();

					// new thread video stream
					// chat
					while (true) {
						display(caller + ": " + in.nextLine());
					}

				}
				// dont accept call
				else if (input == 1 || input==null) {
					f.setVisible(true);
					out.write("0");
					send.doClick();
					display("Connection refused - Closing Socket");
					// socket.close();
				}
			}

		} catch (Exception e) {
			display(e.getMessage());
			e.printStackTrace(System.err);
		}
	}

	

	

	private void display(final String s) {
		EventQueue.invokeLater(new Runnable() {
			// @Override
			public void run() {
				ta.append(s + "\u23CE\n");
			}
		});
	}


}
