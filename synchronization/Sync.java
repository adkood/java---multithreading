package synchronization;

class Counter {

    private int counter = 0;

    public synchronized void increment() {
        counter++;
    }

    // problems with synchronized

    // No fairness
    // Blocking
    // No Interuptability
    // No read/write lock

    public int getCounter() {
        return counter;
    }

}

public class Sync {
    public static void main(String[] args) throws InterruptedException {

        Counter counter = new Counter();

        Thread th1 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                counter.increment();
            }
        });
        
        Thread th2 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                counter.increment();
            }
        });

        th1.start();
        th2.start();

        th1.join();
        th2.join();

        System.out.println(counter.getCounter());
    }
}
