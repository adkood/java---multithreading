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
        lock.lock();
        try {
            System.out.println("Request to withdraw amount : " + amt);
            if (this.balance >= amt) {
                Thread.sleep(2000);
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

    }

}
