package ServerSocket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class Server {

	public void waitForClientAndProcess() {
		
		String lineFromClient = "";

		try {
			// Start Server on port 13007:
			ServerSocket serverSocket = new ServerSocket(13007);

			// Waiting for Client connection
			System.out.println("Waiting for Client connection ...");
			Socket clientSocket = serverSocket.accept();
			InputStream inStream = clientSocket.getInputStream();
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inStream, StandardCharsets.UTF_8));
			PrintWriter outWriter = new PrintWriter(clientSocket.getOutputStream(), true);
			System.out.println("Client connected!");

			// Print welcome message to Client
			this.printMessageToClient("Press 'q' to exit", outWriter);	

			// get lines from Client and exit if 'q' has been pressed
			while (!lineFromClient.equals("q")) {
				lineFromClient = this.getLineFromClient(bufferedReader, inStream);
				System.out.println(">> " + lineFromClient);
			}
			
			System.out.println("\r\nClient pressed 'q'. ==> Exit Server");
			bufferedReader.close();
			outWriter.close();
			serverSocket.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String getLineFromClient(BufferedReader r, InputStream in) throws IOException {

		String str = r.readLine();

		return str;
	}

	public void printMessageToClient(String message, PrintWriter outWriter) {

		outWriter.write(message);
		outWriter.println();
	}
}
