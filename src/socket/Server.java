package socket;
import java.io.IOException;
import java.net.ServerSocket;

public class Server {

    private ServerSocket sS;

    /**
     * Avvia una socket, prendendo come parametro iniziale la porta dove verrà associata
     *
     * @param port
     */
    public Server(int port){
        try {
            sS = new ServerSocket(port);
        }catch (IOException e){}
        System.out.println("Server in ascolto su: " + sS.getLocalPort());
    }

    /**
     * Avvia il server
     */
    public void runServer(){
        while (true)
        {

        }
    }
}
