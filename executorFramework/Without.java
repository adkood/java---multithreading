package executorFramework;

public class Without {

    public int task(int n) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        return n;
    }


    public static void main(String[] args) {
    
        Without without = new Without();

        long start = System.currentTimeMillis();

        Thread[] pool = new Thread[10];
        for(int i = 0; i < 10; i++) {
            int finalI = i;
            pool[i] = new Thread(() -> {
                System.out.println(without.task(finalI));
            });
            pool[i].start();
        }

        for(int i = 0; i < 10; i++) {
            try {
                pool[i].join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        long end = System.currentTimeMillis();

        System.out.println("Time Taken : " + (end-start));
    }

}
