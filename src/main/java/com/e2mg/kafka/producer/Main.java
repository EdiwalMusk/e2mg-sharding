package com.e2mg.kafka.producer;

import com.e2mg.kafka.part.MyPartitioner;
import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;
import java.util.concurrent.ExecutionException;

/**
 * 描述
 *
 * @author EdiwalMusk
 * @date 2023/2/24 18:48
 */
public class Main {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // 1.配置
        Properties properties = new Properties();
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.put(ProducerConfig.PARTITIONER_CLASS_CONFIG, MyPartitioner.class.getCanonicalName());

        // 2.生产者
        KafkaProducer<String, String> producer = new KafkaProducer<>(properties);

        // 3.发送消息
        for (int i = 0; i < 100; i++) {
            producer.send(new ProducerRecord<>("test", "atguigu" + i));
        }

        for (int i = 0; i < 100; i++) {
            producer.send(new ProducerRecord<>("test",  5, "a", "atguigu" + i), new Callback() {
                @Override
                public void onCompletion(RecordMetadata metadata, Exception exception) {
                    System.out.println(metadata.topic());
                    System.out.println(metadata.partition());
                }
            });
        }

//        for (int i = 0; i < 100; i++) {
//            producer.send(new ProducerRecord<>("test", "atguigu" + i)).get();
//        }

        // 4.关闭资源
        producer.close();
    }
}
