package executorFramework;
import java.util.Calendar;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ExecuterServiceExample {
    
    public int task(int n) {
        try {
            Thread.sleep(1000);
        } catch(InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        return n;
    }

    public static void main(String[] args) {
        
        // ExecuterServiceExample exp = new ExecuterServiceExample();

        ExecutorService executorService = Executors.newFixedThreadPool(3);
        // Runnable runnable = new MyRunnable();
        Callable<String> callable = new MyCallable();
        Future<String> future = executorService.submit(callable);
        try {
            System.out.println(future.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        executorService.shutdown();
    }

}
