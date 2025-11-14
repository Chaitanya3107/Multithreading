package locks;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BankAccount {
    private int balance;

    public BankAccount(int balance) {
        this.balance = balance;
    }

    private final Lock lock = new ReentrantLock();

    public void withdraw(int amount){
        System.out.println(Thread.currentThread().getName() + " attempting to withdraw Rs." + amount);
        try {
            if(lock.tryLock(1000, TimeUnit.MILLISECONDS)){
                if(balance>=amount){
                    try{
                        System.out.println(Thread.currentThread().getName() + " proceeding");
                        Thread.sleep(5000); // doing some work
                        balance -=amount;
                        System.out.println(Thread.currentThread().getName() + " Completed withdrawal. Remaining balance is Rs."+ amount);
                    } catch (Exception e) {
                        Thread.currentThread().interrupt();
                    }
                    finally {
                        lock.unlock();// always unlock lock in finally block
                    }

                }else{
                    System.out.println(Thread.currentThread().getName() + " insufficient balance");
                }
            }else{
                System.out.println(Thread.currentThread().getName() + " could not acquire the lock, will try later");
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        if(Thread.currentThread().isInterrupted()){
            System.out.println(Thread.currentThread().getName() + " was interrupted");
        }
    }

//    public synchronized void withdraw(int amount) throws InterruptedException {
//        System.out.println(Thread.currentThread().getName() + " attempting to withdraw Rs." + amount);
//        if(balance>amount){
//            System.out.println(Thread.currentThread().getName() + " proceeding");
//            Thread.sleep(5000); // doing some work
////            balance = balance-amount;
//            balance -=amount;
//            System.out.println(Thread.currentThread().getName() + " Completed withdrawal. Remaining balance is Rs."+ amount);
//        }else {
//            System.out.println(Thread.currentThread().getName() + " insufficient balance");
//        }
//    }
}
