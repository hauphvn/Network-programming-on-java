import java.io.*;
import java.net.Socket;
import java.util.Properties;

public class Client {

    public void connect(){
        Properties prop = new Properties();

        try {
            prop.load((new FileInputStream("config.properties")));
            int servePort = Integer.parseInt(prop.getProperty("serverPort"));
            String serverUrl = prop.getProperty("serverUrl");
            Socket socket  = new Socket(serverUrl, servePort);
            //To connect to server, using PrintStream
            PrintStream ps = new PrintStream(socket.getOutputStream());
            //Sending to server a message
            ps.println("Hello server, i am a client");
            // Waiting for receiving from server
            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String response = br.readLine();
            if (response != null){
                System.out.println("Server answered: "+ response);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        Client client = new Client();
        client.connect();
    }

}
