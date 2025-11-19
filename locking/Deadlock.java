package locking;

import java.util.concurrent.locks.ReentrantLock;

class Pen {

    private final ReentrantLock lock = new ReentrantLock();

    public void getPen() {
        lock.lock();
        try {
            Thread.sleep(1000);
            System.out.println("Pen");
        } catch (Exception e) {

        } finally {
            lock.unlock();
        }
    }

    public void getPaperToWrite(Paper paper) {
        lock.lock();
        paper.getLock().lock();
        try {
            Thread.sleep(500);
            paper.getPaper();
            System.out.println("writing...");
        } catch (InterruptedException e) {
            
        } finally {
            paper.getLock().unlock();
            lock.unlock();
        }
    }

    public ReentrantLock getLock() {
        return lock;
    }

}

class Paper {

    private final ReentrantLock lock = new ReentrantLock();

    public void getPaper() {
        lock.lock();
        try {
            Thread.sleep(1000);
            System.out.println("Paper");
        } catch (Exception e) {

        } finally {
            lock.unlock();
        }
    }

    public void getPenToWrite(Pen pen) {
        pen.getLock().lock();
        lock.lock();
        try {
            Thread.sleep(500);
            pen.getPen();
            System.out.println("writing...");
        } catch (InterruptedException e) {

        } 
        finally {
            lock.unlock();
            pen.getLock().unlock();
        }
    }


    public ReentrantLock getLock() {
        return lock;
    }
}

public class Deadlock {
    public static void main(String[] args) {

        Pen pen = new Pen();
        Paper paper = new Paper();

        Thread th1 = new Thread(() -> {
            pen.getPaperToWrite(paper);
        });

        Thread th2 = new Thread(() -> {
            paper.getPenToWrite(pen);
        });

        th1.start();
        th2.start();
    }
}
