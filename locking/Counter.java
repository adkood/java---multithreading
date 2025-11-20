package locking;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Counter {
    private int count;
    private final ReadWriteLock lock = new ReentrantReadWriteLock();
    private final Lock readLock = lock.readLock();
    private final Lock writeLock = lock.writeLock();

    public void increment() {
        try {
            writeLock.lock();
            count += 1;
        } finally {
            writeLock.unlock();
        }
    }

    public void getCount() {
        try {
            readLock.lock();
            System.out.println(count);
        } finally {
            readLock.unlock();
        }
    }
}
