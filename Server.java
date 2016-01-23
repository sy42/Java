package Socket;

import java.io.InputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	public static void main(String[] args) {
		int data = 0;

		try {
			// Start Server on port 13007:
			ServerSocket serverSocket = new ServerSocket(13007);
			
			// Waiting for Client connect
			System.out.println("Waiting for Client to connect ...");
			Socket clientSocket = serverSocket.accept();			
			System.out.println("Client connected!");
			
			// Print welcome message to Client
			PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
			out.write("Welcome client! Press 'q' to exit.");
			out.println();

			InputStream in = clientSocket.getInputStream();
			String input = "";
			while (!input.equals("q\r")) {
				data = in.read();
				input = input + (char)data; 	
				//System.out.print((char) data);
				if (data == '\n') { 
					input.replace('\n',' ');
					System.out.print(input);
					input = "";
				}
			}

			out.println("'q' pressed ==> Close connection an exit");
			serverSocket.close();
			System.out.println("\r\nClient pressed 'q'. ==> Exit Server");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
