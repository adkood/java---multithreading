package executorFramework;
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
        
        ExecuterServiceExample exp = new ExecuterServiceExample();

        ExecutorService executorService = Executors.newFixedThreadPool(3);

        for(int i = 0; i < 10; i++) {
            int finalI = i;
                
            Future<?> future = executorService.submit(() -> exp.task(finalI));
            
            try {
                System.out.println(future.get());
            } catch (InterruptedException e) {
               Thread.currentThread().interrupt();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }

        }

        executorService.shutdown();
    }

}
