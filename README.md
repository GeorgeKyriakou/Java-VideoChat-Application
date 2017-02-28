#Java-VideoChat-Application
As part of our project for the Connected Systems and Devices course, at Malmö University -Sweden, we develop a Video Chat application written, for the most part in Java (main UI, chat client/host), and in C for the Video stream part of the project (https://github.com/GeorgeKyriakou/AXIS_ImageStream_client_server). The C code runs on an AXIS camera to which the caller connects to and receives the video stream.

##Functionality

The application starts from a class that initiates the main UI, and creates a ServerSocket on a new thread to listen for incoming calls. For each incoming call, a client socket on a new thread is created and the chat happens there.

The Java side of the application consists of the following classes:

	--> StartHere - The class to initiate a new UI (WelcomeUI) and a new listening server (chatServer).
    --> chatServer - Listen for incoming calls and create new thread/ socket for each call
    --> WelcomeUI - This is the main UI of the application. Displayed on the UI are the list of contacts, a call button, an Add
		contact and a Delete contact button. 
    --> handleCalls - Every time a call is made, or received, this class is initiated (along with the video streaming class) taking in
		as parameter a string to let it (the class) know what functionality needs to be implemented (chat caller or chat host), the socket
		if it is hosting a call or null if it is making the call, and finally the name of the contact which we are trying to call (null if
		it is hosting a call).
    --> VideoStream - The video stream class is initiated together with the handleCalls and works together with the AXIS camera to
		receive images, and display them on the chat UI.
    --> ContactList - This class provides the contact list to the WelcomeUI from which we can select one to call. It creates a class
		contact with a constructor (name, ip address, port, camera Ip), and a public class "contactList", which handles the contact
		information, reads all contacts to display from a bin file adds new contacts to and removes them from it (bin file). Then
		inspiration for this contact list came from a post made by Dara Yuk (http://java.worldbestlearningcenter.com/2013/06/contact-
		list.html)

Using the Java Mission Control, we measure the performance of the application over a short period of time (around 10 minutes), and get the
following statistics.
![alt tag](https://writelatex.s3.amazonaws.com/bbtddzytdyqp/uploads/388/10417193/1.png)
