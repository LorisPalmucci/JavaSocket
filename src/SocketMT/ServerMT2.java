package SocketMT;

public class ServerMT2 {
    public static void main(String[] args) {

        /*ServerConnection s1 = new ServerConnection();
        s1.setPort(50007);
        Thread t1 = new Thread(s1);
        t1.start();*/
        ServerConnection s2 = new ServerConnection();
        s2.setPort(50008);
        Thread t2 = new Thread(s2);
        t2.start();
    }
}
