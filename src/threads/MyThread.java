package threads;

public class MyThread extends Thread{
    @Override
    public void run() {
        System.out.println("Running");
        try {
            Thread.sleep(2000);  // t1 thread is running this
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        // main thread is running these process
        MyThread t1 = new MyThread();
        System.out.println(t1.getState()); // new state
        t1.start();
        System.out.println(t1.getState()); // runnable state
//        System.out.println(Thread.currentThread().getName());  // all the above things are done using main thread
        Thread.sleep(1000);// thread stopped,
        System.out.println(t1.getState());
        t1.join(); // waits for current thread to execute completely and then pass the control to main thread
        System.out.println(t1.getState());

    }
}
