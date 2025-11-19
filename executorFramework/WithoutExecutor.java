package executorFramework;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class WithoutExecutor {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        
        ExecutorService executor = Executors.newFixedThreadPool(5);

        Callable<String> c1 = new Callable<String>(() -> "hi");
        
    

    }
}