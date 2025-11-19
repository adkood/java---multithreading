package synchronization;

public class Sync {
    
    public static void main(String[] args) {
    
        Count count = new Count();

        MyThread t1 = new MyThread(count);
        MyThread t2 = new MyThread(count);

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            System.out.println("something went wrong");
        }

        System.out.println(count.getCount());
    }

}
