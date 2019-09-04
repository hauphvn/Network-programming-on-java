import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Properties;

public class Server {

    public void serve() {
        Properties prop = new Properties();

        try {
            System.out.println("Server is really....");
            prop.load(new FileInputStream("config.properties"));
            int servePort = Integer.parseInt(prop.getProperty("serverPort"));
            ServerSocket server = new ServerSocket(servePort);

            Socket socket = server.accept();//This is address that client will connected to.
            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String request = br.readLine();
            if (request != null) {
                System.out.println("Received from a client: " + request);
                // Response to client a message
                PrintStream ps = new PrintStream(socket.getOutputStream());
                ps.println("Hello client, what do you need? ");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Server server = new Server();
        server.serve();
    }
}
