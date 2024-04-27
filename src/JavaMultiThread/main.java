package JavaMultiThread;

public class main {
    public static void main(String[] args) {
        MyThread p1 = new MyThread("Processo 1");
        MyThread p2 = new MyThread("Processo 2");
        Thread t1 = new Thread(p1);
        Thread t2 = new Thread(p2);

        t1.start();
        t2.start();
    }
}