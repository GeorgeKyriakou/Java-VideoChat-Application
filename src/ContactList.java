import java.io.*;
import java.util.HashMap;
import javax.swing.JOptionPane;

class Contact implements Serializable {

	private static final long serialVersionUID = 1L;
	private String cusName;
	private String cusIp;
	private String cusPort;
	private String cusCamIp;

	Contact(String name, String Ip, String Port, String camIp) {

		cusName = name;
		cusIp = Ip;
		cusPort = Port;
		cusCamIp = camIp;

	}

	public String[] getDetails() {
		return new String[] { cusIp, cusPort, cusCamIp };
	}

	public String getName() {
		return cusName;
	}

}

public class ContactList {
	static HashMap<String, Contact> nameList;

	public static String[] viewAll() {
		nameList = importContacts();
		if (nameList != null) {
			String[] contArr = (String[]) nameList.keySet().toArray(new String[nameList.size()]);
			return contArr;
		}
		return null;

	}

	@SuppressWarnings("unchecked")
	public static HashMap<String, Contact> importContacts() {
		HashMap<String, Contact> lObject = null;
		try {
			FileInputStream FileIs = new FileInputStream("ContactList.bin");
			ObjectInputStream ObjIs = new ObjectInputStream(FileIs);
			lObject = (HashMap<String, Contact>) ObjIs.readObject();
			ObjIs.close();

		} catch (Exception ie) {
		}
		return lObject;

	}

	public static void addToList(String name, String Ip, String Port, String camIp) {
		if (nameList == null)
			nameList = new HashMap<String, Contact>();
		Contact st = new Contact(name, Ip, Port, camIp);
		nameList.put(name, st);
		pushToFile(nameList);
	}

	public static void deleteFromList(String name) {
		if (nameList != null) {
			int si = nameList.size();

			Integer input = JOptionPane.showConfirmDialog(null,
					"Are you sure you want to delete " + name + " from contact list?");
			if (input == 0 && input != null) {
				nameList.remove(name); // remove the contact
				if (nameList.size() < si) { // removing is successful
					pushToFile(nameList);
					System.out.println("Contact has been deleted!");
				}
			}
		}
	}

	public static void pushToFile(HashMap<String, Contact> obj) {
		try {
			FileOutputStream FileOS = new FileOutputStream("ContactList.bin");
			ObjectOutputStream ObjOS = new ObjectOutputStream(FileOS);
			ObjOS.writeObject(obj);
			ObjOS.flush();
			ObjOS.close();
			System.out.println("Contact Added!");
		} catch (IOException ie) {
		}

	}

	public static String[] getContactDetails(String name) {

		Contact toCall = nameList.get(name);
		return toCall.getDetails();
	}

}