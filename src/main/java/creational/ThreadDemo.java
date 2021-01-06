package creational;
class MyThread extends Thread {
    static Thread main;
    @Override
    public void run() {
        try {
            main.join();
        }catch (Exception e){

        }
        for(int i = 0; i < 10; i++){
            System.out.println("Child Thread " + i);
        }
    }
}
public class ThreadDemo {
    public static void main(String[] args) {
        MyThread child = new MyThread();
        child.main = Thread.currentThread();
        child.start();
//        try {
//            child.join(1);
//        }catch (Exception e){
//
//        }

        try {
            child.join();
//                Thread.sleep(1000);
        }catch (Exception e){

        }

        for(int i = 0; i < 10; i++){
            System.out.println("Main Thread " + i);



        }
    }
}
