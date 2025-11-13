package threads;

public class DaemonThread extends Thread{

    @Override
    public void run() {
        while(true){
            System.out.println("Hello World");
        }
    }
// Daemon means this thread is not necessary and jvm will not wait for it for its execution to be completed, it only waits for user thread
// after main thread has executed
// Daemon Thread is background thread
    public static void main(String[] args) {
        DaemonThread daemonThread = new DaemonThread();
        daemonThread.setDaemon(true);
        daemonThread.start();
//        now jvm will not wait for daemonThread to finish as it is a background thread now so it will terminate it
        System.out.println("Main Thread done");
    }
}
