package locking;

class Banking {

    private int balance = 100;

    public synchronized void withdraw(int amount) {

        try {
            Thread.sleep(3000);
            if(amount > balance) {
                System.out.println("Insufficient balance");
            } else {
                System.out.println("Amount withdrawn : " + amount );
                balance -= amount;
            } 
        } catch (Exception e) {

        }
    }

    public int getBalance() {
        return balance;
    }

}

public class SynchronizedProblem {
    public static void main(String[] args) {
        
        Banking bank = new Banking();

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                bank.withdraw(50);
            }
        };

        Thread t1 = new Thread(runnable);
        Thread t2 = new Thread(runnable);    

        t1.start();
        t2.start();

        
    }
}
