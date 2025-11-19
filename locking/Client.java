package locking;

public class Client {
    
    public static void main(String[] args) {
        
        Banking banking = new Banking(100);

        Thread th1 = new Thread(() -> {
            banking.withdraw(50);
        });

        Thread th2 = new Thread(() -> {
            banking.withdraw(50);
        });


    }
    
}
