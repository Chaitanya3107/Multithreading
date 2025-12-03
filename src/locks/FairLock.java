package locks;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class FairLock {
    private  final Lock fairLock = new ReentrantLock(true); // now it will provide access on the basis on which thread came first

    public  void accessResource(){
        fairLock.lock();
        try{
            System.out.println(Thread.currentThread().getName() + " acquired the lock");
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        finally {
            System.out.println(Thread.currentThread().getName() + " released the lock");
            fairLock.unlock();
        }
    }

    public static void main(String[] args) {
        FairLock fairLock = new FairLock();
        Runnable task = new Runnable() {
            @Override
            public void run() {
                fairLock.accessResource(); // accessing resource method using thread
            }
        };

        Thread thread1 = new Thread(task,"Thread 1");
        Thread thread2 = new Thread(task,"Thread 2");
        Thread thread3 = new Thread(task,"Thread 3");

        thread1.start();
        thread2.start();
        thread3.start();
    }
}
