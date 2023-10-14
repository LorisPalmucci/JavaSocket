package socket;
import java.io.*;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    private ServerSocket sS;

    /**
     * Avvia una socket, prendendo come parametro iniziale la porta dove verrà associata
     *
     * @param port
     */
    public Server(int port){
        try {
            sS = new ServerSocket();
            sS.bind(new InetSocketAddress(port));
        }catch (IOException e){}
        System.out.println("Server in ascolto su: " + sS.getLocalPort());
    }

    /**
     * Avvia il server
     */
    public void runServer(){
        try (
                Socket sC = sS.accept();
                InputStream inStream = sC.getInputStream();
                BufferedReader buffer = new BufferedReader(new InputStreamReader(inStream));
                FileWriter f = new FileWriter("C:\\Users\\User\\Desktop\\java_rcv_file.json")){
            System.out.println("Connessione stabilita con: " + sC.getRemoteSocketAddress());
            String line;
            while ((line = buffer.readLine()) != null) {
                System.out.println(line);
                f.write(line + "\n");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
