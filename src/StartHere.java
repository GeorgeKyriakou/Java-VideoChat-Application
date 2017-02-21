
public class StartHere {
	public static void main(String[] args) throws Exception {
		WelcomeUI newChat =new WelcomeUI();
		newChat.launcher();
		chatServer chatServe = new chatServer();
		chatServe.start();

	}
}
