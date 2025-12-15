package ThreadCommunication;

class SharedResources{
    private int data;
    private boolean hasData;

    public synchronized void produce(int value){
        while(hasData){ // if there is data in buffer, then wait, don't produce data
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        data = value;
        hasData = true;
        System.out.println("Produced: " + value);
        notify(); // notify other thread about its state

    }

    public synchronized int consume(){
        while (!hasData){
            try {
                wait(); // waiting until hasData is not true
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        hasData = false;
        System.out.println("Consumed: " + data);
        notify(); // notify other thread which is trying to access lock of shared resource
        return data;
    }
}

class Producer implements Runnable{
    private SharedResources resources;

    public Producer(SharedResources resources) {
        this.resources = resources;
    }

    @Override
    public void run() {
        for(int i=0;i<10;i++){
            resources.produce(i);
        }
    }
}
class Consumer implements Runnable{
    private SharedResources resources;

    public Consumer(SharedResources resources) {
        this.resources = resources;
    }

    @Override
    public void run() {
        for(int i=0;i<10;i++){
            int value =  resources.consume();
        }
    }
}

public class ThreadCommunication {
    public static void main(String[] args) {
        SharedResources resources = new SharedResources();
        Thread producerThread = new Thread(new Producer(resources));
        Thread consumerThread = new Thread(new Consumer(resources));
        producerThread.start();
        consumerThread.start();
    }
}











