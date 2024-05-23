package SocketMT;

import java.util.concurrent.TimeUnit;

public class ServerMT {
    public static void main(String[] args) throws InterruptedException {
        //crea la socket impostando la porta e avviando il thread
        ServerConnection s1 = new ServerConnection();
        s1.setPort(50007);
        Thread t1 = new Thread(s1);
        t1.start();
        //aspetta 10 secondi per permettere alla socket precedente di avviarsi correttamente
        TimeUnit.SECONDS.sleep(10);
        //crea una seconda socket
        ServerConnection s2 = new ServerConnection();
        s2.setPort(50008);
        Thread t2 = new Thread(s2);
        t2.start();
    }
}
