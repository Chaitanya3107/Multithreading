package Synchronization;

public class Counter {
    private int count = 0;
//    this causes race condition, when result becomes unpredictable, when multiple thread work on shared resource
//    public void increment(){
//        // is not necessary that it will get 2000 , this is asynchronous
//        count++;
//    }
//    this is called critical section
    public synchronized void increment(){  // synchronised whole method
        // now only one thread will be able to access it at a time, other one will wait
        count++;
    }
//    public void increment(){
//        // synchronized only this block
//        synchronized(this){
//            count++;
//        }
//    }

    public int getCount() {
        return count;
    }

}
