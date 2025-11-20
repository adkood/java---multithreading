package locking;

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
            lock.lock();
            System.out.println("Request to withdraw amount : " + amt);
            try {
                if (this.balance >= amt) {
                    Thread.sleep(3000);
                    this.balance -= amt;
                    System.out.println("Successfully withdrawn amount : " + amt);
                } else {
                    System.out.println("Not enought balance");
                }
            } catch (InterruptedException e) {
                System.out.println("first");
                Thread.currentThread().interrupt();
            } finally {
                lock.unlock();
            }

        } catch (Exception e) {
            System.out.println("second");
            Thread.currentThread().interrupt();
        }

    }

}
