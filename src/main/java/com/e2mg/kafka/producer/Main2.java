package com.e2mg.kafka.producer;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;
import java.util.concurrent.ExecutionException;

/**
 * 描述
 *
 * @author EdiwalMusk
 * @date 2023/2/24 18:48
 */
public class Main2 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // 1.配置
        Properties properties = new Properties();
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        // properties.put(ProducerConfig.PARTITIONER_CLASS_CONFIG, MyPartitioner.class.getCanonicalName());

        // 缓冲区大小
        properties.put(ProducerConfig.BUFFER_MEMORY_CONFIG, 1024 * 1024 * 32);
        // 批次大小
        properties.put(ProducerConfig.BATCH_SIZE_CONFIG, 1024 * 16);
        // linger.ms
        properties.put(ProducerConfig.LINGER_MS_CONFIG, 10000);
        // 压缩大小
        properties.put(ProducerConfig.COMPRESSION_TYPE_CONFIG, "snappy");
        // ack
        properties.put(ProducerConfig.ACKS_CONFIG, 1);
        // 重试次数
        properties.put(ProducerConfig.RETRIES_CONFIG, 3);

        // 2.生产者
        KafkaProducer<String, String> producer = new KafkaProducer<>(properties);

        // 3.发送消息
        for (int i = 0; i < 1; i++) {
            producer.send(new ProducerRecord<>("test", "a" + i));
        }

//        for (int i = 0; i < 100; i++) {
//            producer.send(new ProducerRecord<>("test", "atguigu" + i)).get();
//        }

        Thread.sleep(1000000);

        // 4.关闭资源
        producer.close();
    }
}
