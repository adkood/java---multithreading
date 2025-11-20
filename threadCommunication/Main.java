package threadCommunication;

public class Main {

    private String data;
    private boolean hasData;

    public synchronized void producer(String val) {
        while(hasData) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        this.data = val;
        hasData = true;
        System.out.println("Produced Data : " + data);
        notify();
    }

    public synchronized void consumer() {
        while(!hasData) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        } 
        System.out.println("Consumed Data : " + data);
        this.data = "";
        this.hasData = false;
        notify();       
    }

    public static void main(String[] args) {

        Main main = new Main();

        Thread th1 = new Thread(() -> {
            for(int i = 0; i < 10; i++) {
                main.producer(Integer.toString(i));
            }
        });

        Thread th2 = new Thread(() -> {
            for(int i = 0; i < 10; i++) {
                main.consumer();
            }
        });

        th1.start();
        th2.start();
    }
}
