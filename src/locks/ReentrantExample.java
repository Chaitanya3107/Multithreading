package locks;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantExample {
    private final Lock lock = new ReentrantLock(); // allow lock to reenter again, lock the lock again

    public void outerMethod(){
        lock.lock();
        try {
            System.out.println("Outer Method");
            innerMethod();
        } finally {
            lock.unlock();
        }
    }
    private void innerMethod() {
        lock.lock(); // java make this lock successfully because of ReentrantLock
        try {
            System.out.println("Inner Method");
        } finally {
            lock.unlock();
//            lock.unlock(); causes error because of irregular count of lock and unlock
        }
    }

    public static void main(String[] args) {
        ReentrantExample example = new ReentrantExample();
        example.outerMethod();
    }
}
