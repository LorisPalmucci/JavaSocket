package socket;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;

public class Client {

    private static String HOST = "localhost";
    private static int PORT = 50007;

    Socket socketClient;

    /**
     * Costruttore
     */
    public Client(){
        runClient();
    }

    public void runClient(){
        try {
            System.out.println("Startup Channel...");
            this.socketClient = new Socket();
            System.out.println("Channel created..." + "\n" +
                    "Try to connect...");
                socketClient.connect(new InetSocketAddress(HOST, PORT));
            System.out.println("Connected to: ");
            commandLine();
            System.out.println("Connection closed...");


        }catch (IOException e){
            throw new RuntimeException(e);
        }
    }

    public void commandLine(){
        try {
            BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
            String in ="";
            while (!(in.equals("quit"))) {
                in = buffer.readLine();
                switch (in){
                    case "close":
                        commandList(in);
                        break;
                    default:
                        System.out.println("Command not found");
                        break;
                }
            }
        } catch (IOException e){
            throw new RuntimeException(e);
        }
    }

    public void commandList(String command){
        try {
            OutputStream outStream = socketClient.getOutputStream();
            BufferedWriter b = new BufferedWriter(new OutputStreamWriter(outStream));
            b.write("close");
            b.close();
        }catch (IOException e){
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        Client c = new Client();
    }
}
