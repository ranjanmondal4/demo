package kafka;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;

public class ConsumerKeysDemo2 {
    static Logger logger = LoggerFactory.getLogger(ConsumerKeysDemo2.class);
    static String bootStrapServer = "127.0.0.1:9092";
    static String groupId = "my-first-application";
    static String topic = "first_topic";
    public static void main(String[] args) {

        Properties properties = new Properties();
        properties.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootStrapServer);
        properties.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.setProperty(ConsumerConfig.GROUP_ID_CONFIG, groupId);
        properties.setProperty(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");

        // create consumer
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(properties);;
        //subcribing
        consumer.subscribe(Arrays.asList(topic));
        while (true){
            // poll
            ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(1000));
//            logger.info("list is empty {}", records.isEmpty());
            for (ConsumerRecord record : records){
                logger.info("Key {}, value {}, partition {}, offset {}", record.key(),
                        record.value(), record.partition(), record.offset());
            }
        }



    }
}
