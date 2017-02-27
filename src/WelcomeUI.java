import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.Book;
import java.io.IOException;
import java.net.ServerSocket;
import java.util.Objects;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import org.w3c.dom.css.CSS2Properties;

public class WelcomeUI {
	public ServerSocket ss;
	private static String userId;
	private Font font = new Font("Arial", Font.HANGING_BASELINE, 15);
	private String[] contactArr = ContactList.viewAll();
	public JList<Object> contactList = new JList<Object>(contactArr);

	public static String getUId() {
		return userId;
	}

	public void setUId(String value) {
		userId = value;
	}

	public void launcher() throws ClassNotFoundException, InstantiationException, IllegalAccessException,
			UnsupportedLookAndFeelException, FontFormatException, IOException {

		String UId = JOptionPane.showInputDialog(null, "Username:");
		if (Objects.nonNull(UId))
			setUId(UId);
		
		UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		JFrame launcher = new JFrame();
		final JFrame frame = new JFrame();
		JButton callcontact = new JButton("Call Contact");
		JButton addContact = new JButton("Add new Contact");
		JButton deleteContact = new JButton("Delete Contact");	
		JPanel addRemovePnl = new JPanel();
		GridBagConstraints c = new GridBagConstraints();
		launcher.setVisible(true);
		frame.setLayout(new GridBagLayout());
		frame.setVisible(true);
		frame.setSize(500, 400);
		addRemovePnl.add(addContact);
		addRemovePnl.add(deleteContact);
		c.gridx = GridBagConstraints.CENTER;
		c.gridy = 0;
		frame.add(addRemovePnl,c);	
		c.gridx = GridBagConstraints.CENTER;
		c.gridy = 3;
		c.gridwidth = 3;
		c.fill = GridBagConstraints.HORIZONTAL;
		frame.add(contactList,c);
		c.gridx = GridBagConstraints.CENTER;
		c.gridy = 10;
		frame.add(callcontact,c);
		frame.setFont(font);
		frame.setTitle("Welcome " + getUId());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.repaint();

		
		callcontact.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				new handleCalls("Client", null, (String) contactList.getSelectedValue()).start();
			}
		});
		addContact.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				JTextField name = new JTextField(10);
				JTextField ip = new JTextField(10);
				JTextField port = new JTextField(10);
				JTextField camIP = new JTextField(10);

				JPanel myPanel = new JPanel();
				myPanel.add(new JLabel("Name :"));
				myPanel.add(name);
				myPanel.add(Box.createVerticalStrut(15)); // a spacer
				myPanel.add(new JLabel("IP :"));
				myPanel.add(ip);
				myPanel.add(Box.createVerticalStrut(15)); // a spacer
				myPanel.add(new JLabel("Port :"));
				myPanel.add(port);
				myPanel.add(Box.createVerticalStrut(15)); // a spacer
				myPanel.add(new JLabel("camIP:"));
				myPanel.add(camIP);

				int result = JOptionPane.showConfirmDialog(null, myPanel, "Please enter contact details",
						JOptionPane.OK_CANCEL_OPTION);
				if (result == JOptionPane.OK_OPTION && Objects.nonNull(result)) {
					System.out.println("Name: " + name.getText());
					System.out.println("IP: " + ip.getText());
					System.out.println("Port: " + port.getText());
					System.out.println("camIP: " + camIP.getText());
					ContactList.addToList(name.getText(), ip.getText(), port.getText(), camIP.getText());
					frame.repaint();
				}

			}
		});
		deleteContact.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				ContactList.deleteFromList((String) contactList.getSelectedValue());
			}
		});
	}

}
