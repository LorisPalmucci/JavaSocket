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
        public Server(){runServer();}

        /**
         *
         */
        public void runServer(){
            try {
                System.out.println("Starting socket...");
                sS = new ServerSocket();
                System.out.println("Started...");
                sS.bind(new InetSocketAddress(PORT));
                while(!sS.isClosed()){
                    System.out.println("Server is listening on port: " + sS.getLocalPort());
                    this.sC = sS.accept();
                    System.out.println("Connect to: " + sC.getRemoteSocketAddress() +
                            "\nType 'quit' to close connection..." +
                            "\nWaiting for data...");
                    commandLine();
                    this.sC.close();
                    System.out.println("Client is close...");
                }
                System.out.println("Server is closed!");
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
            try {
                InputStream in = sC.getInputStream();
                BufferedReader buf = new BufferedReader(new InputStreamReader(in));
                boolean flagClose = true;
                String command;
                while (flagClose && !sC.isClosed()){
                    command = buf.readLine();
                    String[] parts = command.split(" ");
                    String comm = parts[0];
                    String argument = parts.length > 1 ? parts[1] : null;
                    if((comm != null))
                    switch (comm){
                        case "quit":
                            System.out.println(comm + ": from " + sC.getRemoteSocketAddress());
                            flagClose = false;
                            break;
                        case "close":
                            System.out.println(comm + ": from " + sC.getRemoteSocketAddress()
                                    +"\nServer start shutdown...");
                            flagClose = false;
                            System.out.println("Server stop listening for new connection...");
                            break;
                        case "get":
                            System.out.println(comm + ": upload file to " + sC.getRemoteSocketAddress());
                            upLoadToClient(argument);

                            break;
                        case "put":
                            System.out.println(comm + ": receive file from " + sC.getRemoteSocketAddress());
                            downloadFromClient();
                            break;
                        default:
                            System.out.println(comm + ": Comm not found");
                            break;
                    }
                    if (!sC.isClosed())
                        sC.close();
                }
                System.out.println("Connection with client is close...");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        /**
         *
         */
        public void upLoadToClient(String a) {
            try {
                if (!sC.isClosed()) {
                    try (OutputStream outStream = sC.getOutputStream();
                         BufferedOutputStream outBuff = new BufferedOutputStream(outStream);
                         FileInputStream file = new FileInputStream(a)) {

                        byte[] buffer = new byte[256];
                        int bytesRead;

                        while ((bytesRead = file.read(buffer)) != -1) {
                            outBuff.write(buffer, 0, bytesRead);
                        }
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                } else {
                    System.out.println("Socket is closed. Cannot write to it.");
                }
            } finally {
                System.out.println("Upload complete!");
            }
        }


        /**
         *
         */
        public void downloadFromClient(){
            try {
                InputStream inStream = sC.getInputStream();
                BufferedInputStream inBuff = new BufferedInputStream(inStream);
                FileOutputStream file = new FileOutputStream("C:\\Users\\User\\Desktop\\RicezioneDalClient.pdf");

                byte[] buffer = new byte[256];
                int bytesWrite;

                while ((bytesWrite = inBuff.read(buffer)) != -1){
                    file.write(buffer, 0, bytesWrite);
                }
                file.close();
                System.out.println("Download complete!");
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

        public void closeSocket(){
            try {
                sS.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
}
