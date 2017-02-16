package chatApp;
import java.net.*;
import java.util.*;

class chatServer extends Thread
{
    static Vector<Socket> ClientSockets;
    
    chatServer() throws Exception
    {
    	
        ServerSocket soc=new ServerSocket(12345);
        ClientSockets=new Vector<Socket>();

        while(true)
        {            	
            Socket CSoc=soc.accept();        
            Thread obClient=new AcceptClient(CSoc);
            obClient.start();
        }
    }
    
    
  

class AcceptClient extends Thread
{
    Socket ClientSocket;
    
    AcceptClient (Socket CSoc) throws Exception
    {
        ClientSocket=CSoc;
        ClientSockets.add(ClientSocket);    
        
    }

    public void run()
    {
    	new handleCalls("Server", ClientSocket).start();
    }
}
}