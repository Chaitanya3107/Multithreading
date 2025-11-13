package threads;

public class InteruptThread extends Thread{

    @Override
    public void run() {
        try {
            Thread.sleep(100);
            System.out.println("Thread is running");
        } catch (InterruptedException e) {
            System.out.println("Thread interrupted");
//            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        InteruptThread t1 = new InteruptThread();
        t1.start();
        t1.interrupt();
    }
}
