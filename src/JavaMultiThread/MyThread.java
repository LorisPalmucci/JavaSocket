package JavaMultiThread;

public class MyThread implements Runnable {
    
    private String nome;
    public MyThread(String n) {
        this.nome = n;
    }

    @Override
    public void run() {
        while (true) {
            try {
                // Simula l'esecuzione del processo per un certo periodo
                System.out.println("In esecuzione: " + nome);
                Thread.sleep(1000); // Esegui il processo ogni secondo
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
