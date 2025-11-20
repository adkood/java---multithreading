package executorFramework;

import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class C1 implements Callable<Integer> {

    private final CyclicBarrier barrier;

    public C1(CyclicBarrier barrier) {
        this.barrier = barrier;
    }

    @Override
    public Integer call() throws Exception {
        try {
            System.out.println("service 1 waiting");
            Thread.sleep(1000);
            barrier.await();
            System.out.println("service 1 completed");
        } catch (Exception e) {
            e.printStackTrace();
        } 

        return 1;
    }
}

class C2 implements Callable<Integer> {

    private final CyclicBarrier barrier;

    public C2(CyclicBarrier barrier) {
        this.barrier = barrier;
    }

    @Override
    public Integer call() throws Exception {
        try {
            System.out.println("service 2 waiting");
            Thread.sleep(2000);
            barrier.await();
            System.out.println("service 2 completed");
        } catch (Exception e) {
            e.printStackTrace();
        } 
        return 2;
    }
}

class C3 implements Callable<Integer> {

    private final CyclicBarrier barrier;

    public C3(CyclicBarrier barrier) {
        this.barrier = barrier;
    }

    @Override
    public Integer call() throws Exception {
        try {
            System.out.println("service 3 waiting");
            Thread.sleep(3000);
            barrier.await();
            System.out.println("service 3 completed");
        } catch (Exception e) {
            e.printStackTrace();
        } 
        return 3;
    }
}

public class ExecuterServiceExample {

    public static void main(String[] args) {

        ExecutorService eService = Executors.newFixedThreadPool(3);

        CyclicBarrier barrier = new CyclicBarrier(3);

        Callable<Integer> a = new C1(barrier);
        Callable<Integer> b = new C2(barrier);
        Callable<Integer> c = new C3(barrier);

        eService.submit(a);
        eService.submit(b);
        eService.submit(c);
        System.out.println("Main");


    }
}
