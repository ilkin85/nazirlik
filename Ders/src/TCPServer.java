import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPServer {
    public static void main(String[] args) throws  Exception {
        ServerSocket ourFirstServerSocket = new ServerSocket(6789);
        while(true) {
            Socket connection = ourFirstServerSocket.accept();
            System.out.println("Bura gelmeyecek");
            InputStream is = connection.getInputStream();
            InputStreamReader reader = new InputStreamReader(is);
            BufferedReader bReader = new BufferedReader(reader);
            String messageFromClient = bReader.readLine();
            System.out.println("message from client = " + messageFromClient);
        }
    }
}
