package com.example.kafkatestproducer;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.KafkaTemplate;

@SpringBootApplication
public class KafkaTestProducerApplication {

    public static void main(String[] args) {
        SpringApplication.run(KafkaTestProducerApplication.class, args);
    }


    @Bean
    public NewTopic topic() {                   //создает топик, если его нет
        return TopicBuilder.name("topic1")
                .partitions(10)
                .replicas(1)
                .build();
    }

    @Bean                   //кафкаТемплет отправляет событие в топик1, а именно "тест"
    public ApplicationRunner runner(KafkaTemplate<String,String>template){
        return args -> {
            template.send("topic1", "test");
        };
    }
}
