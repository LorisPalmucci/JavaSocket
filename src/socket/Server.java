package socket;
import java.io.IOException;
import java.net.ServerSocket;

public class Server {

    private ServerSocket sS;

    /**
     *
     */
    public Server(int port){
        try {
            sS = new ServerSocket(port);
        }catch (IOException e){}
        System.out.println("Server in ascolto su: " + sS.getLocalPort());
    }

    public void runServer(){
        while (true)
        {

        }
    }
}
