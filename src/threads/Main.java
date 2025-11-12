package threads;

public class Main {
    public static void main(String[] args) {
        // Lifecycle of a Thread
        World world = new World(); // New
//        world.start(); // starting the new thread, this is for extends Thread
        // use this way for Runnable interface
        Thread t1 = new Thread(world); // Runnable
        t1.start();

        for( ; ;){
            System.out.println(Thread.currentThread().getName()); // this is the main thread
        }
    }
}