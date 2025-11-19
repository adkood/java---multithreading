package locking;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Banking {

    private int balance = 1000;

    private Lock lock = new ReentrantLock(true);

    public void withdraw(int amount) {
        boolean acquired = false;
        try {
            lock.lockInterruptibly();
            acquired = true;
            System.out.println(Thread.currentThread().getName() + " : grabbed lock");
            Thread.sleep(3000);
            if (amount > balance) {
                System.out.println(Thread.currentThread().getName() + " : No Sufficient balance");
            } else {
                balance -= amount;
                System.out.println(Thread.currentThread().getName() + " : Withdrawn amount : " + amount);
            }

        } catch (InterruptedException e) {
            System.out.println("Interupted!");
        } finally {
            if(acquired) {
                System.out.println(Thread.currentThread().getName() + " : unlocked");
                lock.unlock();
            }
        }
    }

    public int getBalance() {
        return this.balance;
    }
}

public class Locking {

    public static void main(String[] args) throws InterruptedException {

        Banking bank = new Banking();

        Thread th1 = new Thread(() -> {
            bank.withdraw(100);
        });

        Thread th2 = new Thread(() -> {
            bank.withdraw(100);
        });



        th1.start();
        Thread.sleep(100);
        th2.start();

        th2.interrupt();

        th1.join();
        th2.join();

        System.out.println(bank.getBalance());
    }

}