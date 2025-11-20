package locking;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReenterantLocking {

    private final Lock lock = new ReentrantLock(true);
    
    public void A() {
        lock.lock();
        try {
            this.B();
        } finally {
            lock.unlock();
        }
    }

    public void B() {
        lock.lock();
        try {
            this.B();
        } finally {
            lock.unlock();
        }
    }

}
