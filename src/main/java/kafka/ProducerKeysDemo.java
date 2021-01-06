package kafka;

import lombok.SneakyThrows;
import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;
import java.util.Properties;

public class ProducerKeysDemo {
    static Logger logger = LoggerFactory.getLogger(ProducerKeysDemo.class);
    @SneakyThrows
    public static void main(String[] args) {
        String bootStrapServers = "127.0.0.1:9092";
        // create producer properties
        Properties properties = new Properties();
        properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootStrapServers);
        properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.setProperty(ProducerConfig.ENABLE_IDEMPOTENCE_CONFIG, "true");
        properties.setProperty(ProducerConfig.ACKS_CONFIG, "all");
        properties.setProperty(ProducerConfig.MAX_IN_FLIGHT_REQUESTS_PER_CONNECTION, "5");
        properties.setProperty(ProducerConfig.RETRIES_CONFIG, Integer.toString(Integer.MAX_VALUE));

        // create producer
        KafkaProducer<String, String> producer = new KafkaProducer<>(properties);

        String topic = "first_topic";
        for(int i=0;i < 10; i++){
            String message = "message " + i;
            String key = "id_" + i;
            // create an producer record
            ProducerRecord<String, String> record = new ProducerRecord<>(topic, key, message);
            logger.info("Key : {}", key);
            producer.send(record, (recordMetadata, e) -> {
                if(Objects.isNull(e)){
                    logger.info("Metadata, Topic {}, Partition {}, Offset {}, Timestamp {}", recordMetadata.topic(), recordMetadata.partition(),
                            recordMetadata.offset(), recordMetadata.timestamp());
                }else {
                    logger.error("Exception {}", e.getMessage());
                }
            }).get();
        }
        producer.flush();
        producer.close();

    }
}
