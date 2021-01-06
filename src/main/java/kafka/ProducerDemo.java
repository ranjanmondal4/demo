package kafka;

import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;
import java.util.Properties;

public class ProducerDemo {
    static Logger logger = LoggerFactory.getLogger(ProducerDemo.class);
    public static void main(String[] args) {
        String bootStrapServers = "127.0.0.1:9092";
        // create producer properties
        Properties properties = new Properties();
        properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootStrapServers);
        properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        // create producer
        KafkaProducer<String, String> producer = new KafkaProducer<>(properties);

        // create an producer record
        ProducerRecord<String, String> record = new ProducerRecord<>("first_topic", "Another message");

        producer.send(record, new Callback() {
            @Override
            public void onCompletion(RecordMetadata recordMetadata, Exception e) {
                if(Objects.isNull(e)){
                    logger.info("Metadata, Topic {}, Partition {}, Offset {}, Timestamp {}", recordMetadata.topic(), recordMetadata.partition(),
                            recordMetadata.offset(), recordMetadata.timestamp());
                }else {
                    logger.error("Exception {}", e.getMessage());
                }
            }
        });

        producer.flush();
        producer.close();

    }
}
