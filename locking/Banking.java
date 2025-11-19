package locking;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Banking {

    private double balance;

    private final Lock lock = new ReentrantLock();

    public Banking(double balance) {
        this.balance = balance;
    }

    public void withdraw(double amt) {
        
        try {
            if(lock.tryLock(2000, TimeUnit.MILLISECONDS)) {
                System.out.println("Request to withdraw amount : " + amt);
                try {
                    if (this.balance >= amt) {
                        Thread.sleep(1000);
                        this.balance -= amt;
                        System.out.println("Successfully withdrawn amount : " + amt);
                    } else {
                        System.out.println("Not enought balance");
                    }
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                } finally {
                    lock.unlock();
                }
            } else {
                System.out.println("Unable to grab lock");
            }
        } catch (Exception e) {
            Thread.currentThread().interrupt();
        }

    }

}
