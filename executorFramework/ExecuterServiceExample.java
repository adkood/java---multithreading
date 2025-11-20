package executorFramework;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

class Callable1 implements Callable<Integer> {
    @Override
    public Integer call() throws Exception {
        Thread.sleep(2000); 
        System.out.println("Callable 1");
        return 1;
    }
}

class Callable2 implements Callable<Integer> {
    @Override
    public Integer call() throws Exception {
        System.out.println("Callable 2");
        return 2;
    }
}

class Callable3 implements Callable<Integer> {
    @Override
    public Integer call() throws Exception {
        System.out.println("Callable 3");
        return 3;
    }
}

public class ExecuterServiceExample {
    
    public static void main(String[] args) {
        
        ExecutorService service = Executors.newFixedThreadPool(3);

        Callable<Integer> c1 = new Callable1();

        Future<?> future = service.submit(c1);

        future.cancel(true);

        System.out.println(future.isCancelled());

        service.shutdown();
    }   

}
