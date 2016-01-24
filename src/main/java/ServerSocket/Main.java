package ServerSocket;


public class Main {
	public static void main(String[] args) {
		
		Server myServer = new Server();
		
		myServer.waitForClientAndProcess();
	}
}
