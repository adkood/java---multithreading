import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class CompletableFutureExample {

    static CompletableFuture<Integer> getData(int n) {
        return CompletableFuture.supplyAsync(() -> n);  
    } 

    public static void main(String[] args) throws InterruptedException, ExecutionException {

        // ---------------- HANDLING TASK ASYNC -----------------------------------

        // CompletableFuture<String> cf = new CompletableFuture<>();
        // cf.complete("hello");

        // CompletableFuture<Void> cf = CompletableFuture.runAsync(() -> {
        // System.out.println("Running... in bg");
        // });

        // CompletableFuture<Integer> cf = CompletableFuture.supplyAsync(() -> 10);
        // System.out.println(cf.get());
        // cf.join();

        // ----------------- Channing ----------------------------------

        CompletableFuture<Void> cf = CompletableFuture.supplyAsync(() -> 10).thenCompose((n) -> getData(n/0)).thenAccept((r) -> System.out.println("Result : " + r)).exceptionally((e) -> {
            System.out.println("Handling gracefully : " + e);
            return null;
        });

    }

}