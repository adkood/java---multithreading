package threads;

public class B implements Runnable{

    @Override
    public void run() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {

        }

        System.out.println("hello");
    }
    
}