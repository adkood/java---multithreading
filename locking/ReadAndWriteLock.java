package locking;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

class Book {

    private String content;

    private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    private Lock readLock = lock.readLock();
    private Lock writeLock = lock.writeLock();

    public Book() {
        content = "";
    }

    public void writeToBook(String word) {
        writeLock.lock();
        try {
            content += " "+word;
            System.out.println(Thread.currentThread().getName() + " : updated content");
        } finally {
            writeLock.unlock();
        }
    }

    public String readFromBook() {
        readLock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + " : read -> " + content);
            return content;
        } finally {
            readLock.unlock();
        }
    }

}

public class ReadAndWriteLock {
    public static void main(String[] args) {

        Book book = new Book();

        Thread th1 = new Thread(() -> {
            for(int i = 0; i < 5; i++) {
                book.writeToBook(""+i);
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {

                }
            }
        });

        Thread th2 = new Thread(() -> {
            for(int i = 0; i < 5; i++) {
                book.readFromBook();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    
                }
            }
        });

        Thread th3 = new Thread(() -> {
            for(int i = 0; i < 5; i++) {
                book.readFromBook();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    
                }
            }
        });

        th1.start();
        th2.start();
        th3.start();
    }
}
