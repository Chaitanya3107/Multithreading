package threads;

public class YieldThread extends Thread{
    public YieldThread(String name) {
        super(name);
    }

    @Override
    public void run() {
        for(int i=0;i<20;i++){
            System.out.println(Thread.currentThread().getName()+ " is running");
            Thread.yield();/// tells thread that other thread is also available to work and can use that too
        }
    }

    public static void main(String[] args) {
        YieldThread t1 = new YieldThread("t1");
        YieldThread t2 = new YieldThread("t2");
        t1.start();
        t2.start();
    }
}
