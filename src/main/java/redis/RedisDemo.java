package redis;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.Arrays;
import java.util.List;


public class RedisDemo {

    JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
    public JedisPool pool = new JedisPool(jedisPoolConfig,"localhost");
/*
    RedisDemo(){
        RedisTemplate<String, Student> t = new RedisTemplate<>();
        t.setConnectionFactory(jedisPoolConfig);
        t.setKeySerializer(new StringRedisSerializer());
        t.setValueSerializer(new Jackson2JsonRedisSerializer<>(Trade.class));
        t.afterPropertiesSet();
    }*/

    public void test() {
        try {
            Jedis jedis = pool.getResource();

//            List<Student> students = Arrays.asList(Student.of("Ranjan", 20));

//            String[] items = new String[]{"TV", "Fridge"};
//            jedis.lpush("user-1", items);
            jedis.lpush("user-1", Student.of("Ranjan", 20).toString());
            jedis.lpush("user-1", Student.of("Rohan", 30).toString());

            List<String> items = jedis.lrange("user-1", 0 , -1);
            items.forEach(System.out::println);
//            String item = jedis.lpop("user-1");
        } catch (Exception e){
            System.err.println(e.toString());
        } finally {
            pool.destroy();
        }
    }

    public static void main(String[] args) {
        RedisDemo helloWorld = new RedisDemo();
        helloWorld.test();
    }


}

@Data
@AllArgsConstructor(staticName = "of")
class Student {
    private String name;
    private int age;
}