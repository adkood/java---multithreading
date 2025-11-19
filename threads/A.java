package threads;

public class A extends Thread {

    @Override
    public void run() {
    
        try {
            Thread.sleep(3000);
        } catch(InterruptedException e) {

        }
        
        System.out.println("Task completed");
    }
}