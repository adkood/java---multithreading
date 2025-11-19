package threadCommunication;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

class Resource {

    private BlockingQueue<Integer> q;

    public Resource(int cap, boolean fair) {
        q = new ArrayBlockingQueue<Integer>(cap, fair);
    }

    public void produce(int value) {
        try {
            q.put(value);
            System.out.println(Thread.currentThread().getName() + " : data produced!");
        } catch (InterruptedException e) {

        }
    }

    public void consume() {
        try {
            int data = q.take();
            System.out.println(Thread.currentThread().getName() + " : Consumed: " + data);
        } catch (InterruptedException e) {

        }
    }
}

public class Main {
    public static void main(String[] args) throws InterruptedException {

        Resource resource = new Resource(1,true);

        Thread th1 = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                resource.produce(i);
            }
        });

        Thread th2 = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                resource.consume();
            }
        });

        Thread th3 = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                resource.consume();
            }
        });

        th1.start();
        th2.start();
        th3.start();

        th1.join();
        th2.join();
        th3.join();
    }
}
