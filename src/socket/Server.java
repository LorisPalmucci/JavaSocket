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
<<<<<<< HEAD
    public void runServer(int socketPort){
        try {
            System.out.println("Starting socket...");
            sS = new ServerSocket();
            System.out.println("Started...");
            sS.bind(new InetSocketAddress(socketPort));
            System.out.println("Server is listening on port: " + sS.getLocalPort());
            Socket sC = sS.accept();
            System.out.println("Connect to: " + sC.getRemoteSocketAddress());
            transferFile(sC);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Avvia la trascrizione del file sulla specifica socket passata.
     *
     * inStream: la variabile prende in ingresso il flusso di byte dalla socket e li immagazzina.
     * Buffer: crea una nuovo oggetto che legge dallo stream di caratteri, gestito a sua volta tramite una istanza
     *          InputStreamReader.
     * InputStreamReader: Quest'ultima funge da ponte tra il flusso di byte della socket e lo stram di
     *          caratteri che verranno bufferizzati.
     * FileWriter: scrive i caratteri bufferizzati in un file a cui viene passata la path assoluta di salvataggio.
     *
     * @param socket
     *              la socket da cui viene prelevato il flusso di byte.
     */
<<<<<<< HEAD
=======
>>>>>>> parent of 0247f2c (split code into more method)
    public void runServer(){
        try (
                Socket sC = sS.accept();
                InputStream inStream = sC.getInputStream();
                BufferedReader buffer = new BufferedReader(new InputStreamReader(inStream));
                FileWriter f = new FileWriter("C:\\Users\\User\\Desktop\\java_rcv_file.json")){
            System.out.println("Connessione stabilita con: " + sC.getRemoteSocketAddress());
<<<<<<< HEAD
=======
    private void transferFile(Socket socket){
        try(InputStream inStream = socket.getInputStream();
            BufferedReader buffer = new BufferedReader(new InputStreamReader(inStream));
            FileWriter f = new FileWriter("C:\\Users\\User\\Desktop\\java_rcv_file.json")) {

>>>>>>> receiveFile
=======
            //receiveAndWriteToFile(sC);
>>>>>>> parent of 0247f2c (split code into more method)
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
