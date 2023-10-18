package socket;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;

public class Client {

    private static String HOST = "localhost";
    private static int PORT = 50007;

    /**
     * Costruttore
     */
    public Client(){
        runClient(HOST, PORT);
    }

    private void runClient(String hostAdd, int hostPort){
        try {
            System.out.println("Startup Channel...");
            Socket socketClient = new Socket();
            System.out.println("Channel created..." + "\n" +
                    "Try to connect...");
            socketClient.connect(new InetSocketAddress(hostAdd, hostPort));
            System.out.println("Connected to: ");
            System.out.println("Connection closed...");


        }catch (IOException e){
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        Client c = new Client();
    }
}
