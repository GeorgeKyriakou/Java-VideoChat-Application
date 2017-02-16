//package ClientThread;
package chatApp;

import java.io.*;
import java.net.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;

class ClientClass extends Thread {
	private static String ip = null;
	
	ClientClass(String ip){
		this.ip=ip;
	}
	public void run() {
		Socket socket = null;
		DataInputStream input = null;
		try {
			socket = new Socket(ip, 5005);
			input = new DataInputStream(socket.getInputStream());

		} catch (UnknownHostException e1) {

			e1.printStackTrace();
		} catch (IOException e1) {

			e1.printStackTrace();
		}

		try {

			OutputStreamWriter osw = new OutputStreamWriter(
					socket.getOutputStream(), "UTF-8");

			String res = "480x270";
			osw.write(res, 0, res.length());
			osw.flush();

			String fps = "25";
			osw.write(fps, 0, fps.length());
			osw.flush();
			System.out.println("Resolution sent: " + res);

			while (true) {
				if (socket.isConnected()) {

					BufferedImage bImg = null;
					int picsize = input.readInt();
					byte[] img = new byte[picsize];

					int x = 0;

					while (true) {
						int read = input.read(img, x, picsize - x);
						x = x + read;
						if (x == picsize)
							break;

					}

					InputStream in = new ByteArrayInputStream(img);

					bImg = ImageIO.read(in);

					ImageIcon image = new ImageIcon(bImg);
					handleCalls.label.setIcon(image);
					handleCalls.label.repaint();
					handleCalls.label.validate();

				}
			}

		} catch (IOException e) {
			System.out.println("Error 2... ");
		}

	}

}

// public class VideoStream {

// public static void main(String args[]) throws UnknownHostException,
// IOException {

// Thread myClass = new ClientClass();
// myClass.start();

// }

// }
