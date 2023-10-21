package socket;
import java.io.*;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    private ServerSocket sS;

    private Socket sC;

    private static int PORT = 50007;

    /**
     * Avvia una socket, prendendo come parametro iniziale la porta dove verrà associata
     */
    public Server(){
        runServer();
    }

    /**
     *
     */
    public void runServer(){
        try {
            System.out.println("Starting socket...");
            sS = new ServerSocket();
            System.out.println("Started...");
            sS.bind(new InetSocketAddress(PORT));
            System.out.println("Server is listening on port: " + sS.getLocalPort());
            while(!sS.isClosed()){
                this.sC = sS.accept();
                System.out.println("Connect to: " + sC.getRemoteSocketAddress() +
                        "\nType 'quit' to close connection..." +
                        "\nWaiting for data...");
                commandLine();
                System.out.println("Server is listening on port: " + sS.getLocalPort());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Avvia la trascrizione del file sulla specifica socket passata.
     *
     * in: la variabile prende in ingresso il flusso di byte dalla socket e li immagazzina.
     * buf: crea una nuovo oggetto che legge dallo stream di caratteri, gestito a sua volta tramite una istanza
     *          InputStreamReader.
     * InputStreamReader: Quest'ultima funge da ponte tra il flusso di byte della socket e lo stream di
     *          caratteri che verranno bufferizzati.
     */
    private void commandLine(){
        String s;
        try {
            do{
                InputStream in = sC.getInputStream();
                BufferedReader buf = new BufferedReader(new InputStreamReader(in));
                s = buf.readLine();
                System.out.println(s);
            }while(!s.equals("quit"));
            System.out.println("Send 'close' from client...");
            sC.close();
            System.out.println("Connection close...");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     *
     */
    public void upLoadToClient(){
        try {
            OutputStream outStream = sC.getOutputStream();
            BufferedOutputStream outBuff = new BufferedOutputStream(outStream);
            FileInputStream file = new FileInputStream("C:\\Users\\User\\Desktop\\ricezione.txt");

            byte[] buffer = new byte[256];
            int bytesRead;

            while ((bytesRead = file.read(buffer)) != -1){
                outBuff.write(buffer, 0, bytesRead);
            }
            outBuff.close();

        } catch (IOException e){
            throw new RuntimeException(e);
        }
    }

    /**
     *
     * @return
     */
    public boolean checkIsBound(){return sS.isBound();}

    /**
     *
     * @return
     */
    public boolean checkIsClosed(){return sS.isClosed();}
}
