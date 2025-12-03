package locks;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteCounter {

    private int count = 0;
    private final ReadWriteLock lock = new ReentrantReadWriteLock(); // give us readLock and writeLock
    private final Lock readLock = lock.readLock();
    private final Lock writeLock = lock.writeLock();

    public void increment(){
        writeLock.lock();
        try {
            count++;

        } finally {
            writeLock.unlock();
        }
    }

    public int getCount() {
        readLock.lock(); // multiple threads can acquire this lock
        try {
            return count;
        } finally {
            readLock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ReadWriteCounter counter = new ReadWriteCounter();

        Runnable ReadTask = new Runnable() {
            @Override
            public void run() {
                for(int i=0;i<10;i++){
                    System.out.println(Thread.currentThread().getName() + " read: " + counter.getCount());
                }
            }
        };
        Runnable WriteTask = new Runnable() {
            @Override
            public void run() {
                for(int i=0;i<10;i++){
                    counter.increment();
                    System.out.println(Thread.currentThread().getName() + " incremented" );
                }
            }
        };

        Thread writeThread = new Thread(WriteTask);
        Thread readerThread1 = new Thread(ReadTask);
        Thread readerThread2 = new Thread(ReadTask);

        writeThread.start();
        readerThread1.start();
        readerThread2.start();

        writeThread.join();
        readerThread1.join();
        readerThread2.join();

        System.out.println("Final count: " + counter.getCount());
    }
}
