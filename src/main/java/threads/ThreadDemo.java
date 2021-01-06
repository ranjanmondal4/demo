package threads;

import lombok.SneakyThrows;

import java.util.concurrent.*;

public class ThreadDemo {
    @SneakyThrows
    public static void main(String[] args) {
        ExecutorService service = Executors.newFixedThreadPool(2);
        service.execute(() -> System.out.println("name " + Thread.currentThread().getName()));
        service.execute(() -> System.out.println("name " + Thread.currentThread().getName()));
        service.execute(() -> System.out.println("name " + Thread.currentThread().getName()));
        service.execute(() -> System.out.println("name " + Thread.currentThread().getName()));
        Future<String> future = service.submit(new MyCallable());
        future.cancel(true);
        String name = future.get(1000, TimeUnit.MILLISECONDS);
        System.out.println(name);
        service.shutdown();
    }
}

class MyCallable implements Callable<String>{

    @Override
    @SneakyThrows
    public String call() throws Exception {
        Thread.sleep(10000);
        return "Sorry, I am late";
    }
}
class Producer implements Runnable {

    private BlockingQueue queue;

    public Producer(BlockingQueue queue) {
        this.queue = queue;
    }

    @Override
    @SneakyThrows
    public void run() {
        queue.put("Ranjan");
        Thread.sleep(1000);
        queue.put("Shubham");
        Thread.sleep(1000);
        queue.put("Arif");
    }
}
class Consumer implements Runnable {

    private BlockingQueue queue;

    public Consumer(BlockingQueue queue) {
        this.queue = queue;
    }

    @Override
    @SneakyThrows
    public void run() {
        Thread.sleep(1000);
        String name = (String) queue.poll();
        System.out.println(name);
        Thread.sleep(1000);
        name = (String) queue.poll();
        System.out.println(name);
        Thread.sleep(1000);
        name = (String) queue.poll();
        System.out.println(name);
    }
}

class MyRunnable implements Runnable {
    private Shared shared;

    public MyRunnable(Shared shared) {
        this.shared = shared;
    }

    @Override
    @SneakyThrows
    public void run() {
        synchronized (this.shared){
            if(!shared.isNotified){
                System.out.println("First thread goes on wait");
                this.shared.wait();
                System.out.println("First thread, waiting is over");
            }
        }
        /*IntStream.rangeClosed(1, 5).forEach((int i) -> {
            shared.setCount();
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Child thread running  " + Thread.currentThread().getName() + ",  i " + i + ", count " + shared.getCount());
        });*/
    }
}

class MyRunnableTwo implements Runnable {
    private Shared shared;

    public MyRunnableTwo(Shared shared) {
        this.shared = shared;
    }

    @Override
    public void run() {
        synchronized (shared){
            this.shared.notify();
            this.shared.isNotified = true;
            System.out.println("The notified by second thread");
        }
        /*IntStream.rangeClosed(1, 5).forEach((int i) -> {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Child thread running  " + Thread.currentThread().getName() + ",  i " + i + ", count " + shared.getCount());
        });*/
    }
}

class Shared {
    private volatile int count = 0;
    public boolean isNotified = false;

    public int getCount() {
        return count;
    }

    public void setCount() {
        ++this.count;
    }
}