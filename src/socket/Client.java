package socket;

import com.sun.source.tree.WhileLoopTree;

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
            this.socketClient.connect(new InetSocketAddress(HOST, PORT));
            System.out.println("Connected to: " + this.socketClient.getRemoteSocketAddress());
            commandLine();
            this.socketClient.close();
            System.out.println("Connection closed...");


        }catch (IOException e){
            throw new RuntimeException(e);
        }
    }

    public void commandLine(){
        try {
            BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
            String in ="";
            while (!(in.equals("quit")) && !socketClient.isClosed()) {
                in = buffer.readLine();
                sendCommand(in);
            }
        } catch (IOException e){
            throw new RuntimeException(e);
        }
    }
    private void sendCommand(String command){
        try {
            OutputStream outStream = socketClient.getOutputStream();
            BufferedWriter b = new BufferedWriter(new OutputStreamWriter(outStream));
            switch (command){
                case "quit":
                    b.write(command);
                    b.newLine();
                    b.flush();
                case "put":
                    b.write(command);
                    b.newLine();
                    b.flush();
                    upLoadToServer();
                    break;
                case "get":
                    b.write(command);
                    b.newLine();
                    b.flush();
                    downloadFromServer();
                    break;
                default:
                    System.out.println("comm not found");
            }
        }catch (IOException e){
            throw new RuntimeException(e);
        }
    }

    public void upLoadToServer(){
        try {
            OutputStream outStream = socketClient.getOutputStream();
            BufferedOutputStream outBuff = new BufferedOutputStream(outStream);
            FileInputStream file = new FileInputStream("C:\\Users\\User\\Desktop\\linux.txt");

            byte[] buffer = new byte[256];
            int byteSRead;

            while ((byteSRead = file.read(buffer)) != -1){
                outBuff.write(buffer, 0, byteSRead);
            }

        }catch (IOException e){
            throw new RuntimeException(e);
        }
    }

    public void downloadFromServer(){
        try {
            InputStream inStream = socketClient.getInputStream();
            BufferedInputStream inBuff = new BufferedInputStream(inStream);
            FileOutputStream file = new FileOutputStream("C:\\Users\\User\\Desktop\\ricezione.txt");

            byte[] buffer = new byte[256];
            int bytesWrite;

            while ((bytesWrite = inBuff.read(buffer)) != -1){
                file.write(buffer, 0, bytesWrite);
            }
            file.close();

        }catch (IOException e){
            throw new RuntimeException(e);
        }
    }


    public static void main(String[] args) {
        Client c = new Client();
    }
}
