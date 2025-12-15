package Deadlocks;


class Pen{
     public synchronized void writeWithPenAndPaper(Paper paper){
         System.out.println(Thread.currentThread().getName() + " is using pen " + this + " are trying to use paper " +  paper.toString());
         paper.finishWriting();
     }

     public synchronized void finishWriting(){
         System.out.println(Thread.currentThread().getName() + " finished using pen " + this);
     }
}

class Paper{
    public synchronized void writeWithPaperAndPen(Pen pen){
        System.out.println(Thread.currentThread().getName() + " is using paper " + this + " are trying to use pen " + pen.toString());
        pen.finishWriting();
    }

    public synchronized void finishWriting(){
        System.out.println(Thread.currentThread().getName() + " finished using paper " + this);
    }

}

class Task1 implements Runnable{
    private Pen pen;
    private Paper paper;

    public Task1(Pen pen, Paper paper) {
        this.pen = pen;
        this.paper = paper;
    }

    @Override
    public void run() {
        pen.writeWithPenAndPaper(paper); // thread1 locks pen and tries to lock paper
    }
}
class Task2 implements Runnable{
    private Pen pen;
    private Paper paper;

    public Task2(Pen pen, Paper paper) {
        this.pen = pen;
        this.paper = paper;
    }

    @Override
    public void run() {
        synchronized (pen){
            paper.writeWithPaperAndPen(pen); // now it needs to get pen lock first to get paper lock
        }
//        paper.writeWithPaperAndPen(pen); // thread2 locks paper and tries to lock pen
    }
}

public class DeadLockExamples {
    public static void main(String[] args) {
        Pen pen = new Pen();
        Paper paper = new Paper();

        Thread thread1 = new Thread(new Task1(pen, paper), "Thread 1");
        Thread thread2 = new Thread(new Task2(pen, paper), "Thread 2");

        thread1.start();
        thread2.start();

        // solve deadlock by just changing the order of acquiring locks,
        // before giving paper lock of pen, make sure it is synchronised, so it needs pen lock to proceed
    }

}









