package lifecycle;

public class LifeCycle extends Thread {

    @Override
    public void run() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // for (int i = 0; i < 10000; i++) {
        // System.out.println(Thread.currentThread().getName());
        // }
    }

    public static void main(String[] args) throws InterruptedException {

        LifeCycle th = new LifeCycle();
        System.out.println(th.getState()); // NEW

        th.start();
        System.out.println(th.getState()); // RUNNABLE

        Thread.sleep(100);
        System.out.println(th.getState()); // TIMED WAITING

        th.join();
        System.out.println(th.getState()); // TERMINATED
    }
}
