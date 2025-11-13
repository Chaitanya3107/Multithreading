package threads;

public class PriorityThread extends Thread {
    public PriorityThread(String name) {
        super(name);
    }

    @Override
    public void run() {
        System.out.println("Thread is running");
        for(int i=0;i<100000000;i++){
                String a = " ";
            for(int j=0;j<100000;j++){
                a += "a";
            }
            System.out.println(Thread.currentThread().getName()+" Priority is - "+Thread.currentThread().getPriority()+ " Count is - "+ i);
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }


//        for(int j=0;j<5;j++){
//            System.out.println(Thread.currentThread().getName()+" Priority is - "+Thread.currentThread().getPriority()+ " Count is - "+ i);
//            try {
//                Thread.sleep(100);
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
////            System.out.println(i);
//        }
    }

    public static void main(String[] args) throws InterruptedException {
//        Thread1 t1 = new Thread1("Max");
//        t1.start();
//        t1.setPriority(Thread.MIN_PRIORITY);
//        t1.join();
//        System.out.println("Will be printed after 5 second");
        PriorityThread l = new PriorityThread("Low priority Thread");
        PriorityThread m = new PriorityThread("Normal priority Thread");
        PriorityThread h = new PriorityThread("High priority Thread");
        l.setPriority(MIN_PRIORITY);
        m.setPriority(NORM_PRIORITY);
        h.setPriority(MAX_PRIORITY);
        l.start();
        m.start();
        h.start();
    }
}

