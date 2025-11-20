package executorFramework;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ExecuterServiceExample {
    
    public static void main(String[] args) {
        
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(3);
        scheduler.scheduleWithFixedDelay(() -> System.out.println("Running after 2 seconds"), 1000, 2000, TimeUnit.MILLISECONDS);


        scheduler.schedule(() -> scheduler.shutdown(), 10000, TimeUnit.MILLISECONDS);
    }   
}
