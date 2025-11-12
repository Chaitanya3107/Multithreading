package threads;//public class threads.World extends Thread{
//    @Override
//    public void run() {
//        for( ; ;){
//            System.out.println(Thread.currentThread().getName()); // this is the main thread
//        }
//
//    }
//}

public class World implements Runnable{
    @Override
    public void run() {
        for( ; ;){
            System.out.println(Thread.currentThread().getName()); // this is the main thread
        }

    }
}
