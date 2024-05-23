package SocketMT;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerConnection implements Runnable {
    //variabile per la socket del server
    private ServerSocket sS;

    //porta su cui il server sarà in ascolto
    private static int PORT;

    /**
     * Avvia il server. Viene creata una nuova socket server e associata ad una porta. Fincé la socket non viene chiusa
     * rimane in ascolto sulla porta in attesa di una connessione da parte di un client.
     *
     * Quando arriva una connessione, viene accettata e quindi creata la connessione Server/Client.
     * Il client rimane in attesa di un input da parte dell'utente e la connessione viene chiusa quando termina il
     * suo compito.
     */
    @Override
    public void run() {
        try {
            InetAddress IP = InetAddress.getByName("127.0.0.1");
            System.out.println("Starting socket...");
            sS = new ServerSocket();
            sS.bind(new InetSocketAddress(IP, PORT));
            System.out.println("Started..."+ sS);
            while(!sS.isClosed()){
                System.out.println("Server is listening on port: " + sS.getLocalPort());
                //varibile per la socket del client
                Socket sC = sS.accept();
            }
            System.out.println("Server is closed!");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Setter che serve ad impostare la porta per la socket specifica
     *
     * @param p
     */
    public void setPort(int p){
        this.PORT = p;
    }
}
