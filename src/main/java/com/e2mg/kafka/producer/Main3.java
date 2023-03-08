package com.e2mg.kafka.producer;

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
public class Main3 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // 1.配置
        Properties properties = new Properties();
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "192.168.94.195:9092");
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        // properties.put(ProducerConfig.PARTITIONER_CLASS_CONFIG, MyPartitioner.class.getCanonicalName());

        // 缓冲区大小
        properties.put(ProducerConfig.BUFFER_MEMORY_CONFIG, 1024 * 1024 * 32);
        // 批次大小
        properties.put(ProducerConfig.BATCH_SIZE_CONFIG, 1024 * 1);
        // linger.ms
        properties.put(ProducerConfig.LINGER_MS_CONFIG, 100);
        // 压缩算法
        // properties.put(ProducerConfig.COMPRESSION_TYPE_CONFIG, "snappy");
        // ack
        properties.put(ProducerConfig.ACKS_CONFIG, "1");
        // 重试次数
        properties.put(ProducerConfig.RETRIES_CONFIG, 3);
        // 事务id
        // properties.put(ProducerConfig.TRANSACTIONAL_ID_CONFIG, "transaction_id_01");

        // 2.生产者
        KafkaProducer<String, String> producer = new KafkaProducer<>(properties);

        // 开启事务
        // producer.initTransactions();
        // producer.beginTransaction();
        // 3.发送消息
        for (int i = 0; i < 10000; i++) {
            String value = "atguigu" + i;
            producer.send(new ProducerRecord<>("test302", 0, value, value), new Callback() {
                @Override
                public void onCompletion(RecordMetadata metadata, Exception exception) {
                    System.out.println(metadata.topic());
                    System.out.println(metadata.partition());
                }
            });
        }
        // 结束事务
        // Thread.sleep(10000);
        // producer.abortTransaction();

        // Thread.sleep(1000000);

        // 4.关闭资源
        producer.close();
    }
}
