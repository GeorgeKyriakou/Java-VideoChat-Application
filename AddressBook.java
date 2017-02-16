package chatApp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AddressBook {
	public static HashMap<String, HashMap<String, String>> Contacts = new HashMap<>();

	public static String[] Book() {
		HashMap<String, String> Mike = new HashMap<>();
		HashMap<String, String> George = new HashMap<>();
		HashMap<String, String> Angelos = new HashMap<>();
		HashMap<String, String> Mohammed = new HashMap<>();
		HashMap<String, String> Caroline = new HashMap<>();

		Mike.put("Port", "12345");
		Mike.put("Ip", "127.0.0.1");
		Mike.put("CamIp", "192.168.20.246");
		Mohammed.put("Port", "12345");
		Mohammed.put("Ip", "");
		Angelos.put("Port", "12345");
		Angelos.put("Ip", "192.168.20.245");
		Angelos.put("CamIp", "192.168.20.246");
		Caroline.put("Port", "12345");
		Caroline.put("Ip", "127.0.0.1");
		George.put("Port", "12345");
		George.put("Ip", "127.0.0.1");

		Contacts.put("Mike", Mike);
		Contacts.put("Mohammed", Mohammed);
		Contacts.put("George", George);
		Contacts.put("Angelos", Angelos);
		Contacts.put("Caroline", Caroline);

		List<String> keyList = new ArrayList<String>(Contacts.keySet());
		String[] stockArr = new String[keyList.size()];
		stockArr = keyList.toArray(stockArr);
		return stockArr;
	}

	public static String[] getParam(String selected) {

		String Ip = ((HashMap<String, String>) Contacts.get(selected)).get("Ip").toString();
		String Port = ((HashMap<String, String>) Contacts.get(selected)).get("Port").toString();
		String camIp = ((HashMap<String, String>) Contacts.get(selected)).get("CamIp").toString();

		return new String[] { Ip, Port, camIp };
	}

	public static void addNewContact(String name, String Ip, String port) {
		HashMap<String, String> newContact = new HashMap<>();
		newContact.put("Ip", Ip);
		newContact.put("Port", port);
		Contacts.put(name, newContact);
	}
}