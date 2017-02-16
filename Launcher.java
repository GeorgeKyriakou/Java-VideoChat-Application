package chatApp;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.ServerSocket;
import java.util.Objects;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class Launcher {
	public static ServerSocket ss;
	private static String userId;
	private static Font font = new Font("Arial", Font.HANGING_BASELINE, 15);
	private static String[] contactArr = AddressBook.Book();
	public static JList<Object> contactList = new JList<Object>(contactArr);

	public static String getUId() {
		return userId;
	}

	public static void setUId(String value) {
		userId = value;
	}

	public static void main(String[] args) throws Exception {
		launcher();
		chatServer chatServe = new chatServer();
		chatServe.start();

	}

	public static void launcher() throws ClassNotFoundException, InstantiationException, IllegalAccessException,
			UnsupportedLookAndFeelException, FontFormatException, IOException {

		//UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		JFrame launcher = new JFrame();
		JFrame frame = new JFrame();
		JButton button = new JButton("Call Contact");
		JButton addContact = new JButton("Add new Contact");
		String UId = JOptionPane.showInputDialog(null, "Username:");
		if (UId!=null)
			setUId(UId);
		launcher.setVisible(true);
		frame.setVisible(true);
		frame.setSize(500, 400);
		frame.add(contactList);
		frame.setFont(font);
		frame.setTitle("Welcome " + getUId());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(button, BorderLayout.NORTH);
		frame.add(addContact, BorderLayout.SOUTH);
		frame.repaint();

		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				new handleCalls("Client", null).start();
			}
		});
		addContact.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				String name = JOptionPane.showInputDialog("Name", null);
				String Ip = JOptionPane.showInputDialog("Ip Address", null);
				String port = JOptionPane.showInputDialog("Port Number", null);
				AddressBook.addNewContact(name, Ip, port);

			}

		});
	}

}
