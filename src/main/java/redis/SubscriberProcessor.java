package redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class SubscriberProcessor implements Runnable{
    private JedisPool pool = new JedisPool(new JedisPoolConfig(),
            "localhost");
    private Subscriber subscriber = new Subscriber();
    private Thread simpleThread;
    private Jedis jedis = getResource();
    public Jedis getResource() {
        jedis = pool.getResource();
        return jedis;
    }
    public void setResource(Jedis jedis){
//        pool.returnResource(jedis);
    }
    @SuppressWarnings("static-access")
    public static void main(String[] args) {
        SubscriberProcessor test = new SubscriberProcessor();
        test.subscriberProcessor();
//        while (true){
//
//        }
//        try {
//            Thread.currentThread().sleep(10000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        test.unsubscribe();
    }
    private void unsubscribe() {
        simpleThread.interrupt();
        if(subscriber.isSubscribed()){
            subscriber.unsubscribe();
        }
    }
    private void subscriberProcessor() {
        simpleThread = new Thread(this);
        simpleThread.start();
    }
    @Override
    public void run() {
//        while (!Thread.currentThread().isInterrupted()) {
            jedis.subscribe(subscriber, "news");
//            jedis.psubscribe(subscriber, "news.*");
//        }
    }
}