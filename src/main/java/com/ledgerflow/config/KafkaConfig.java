package com.ledgerflow.config;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.*;
import org.springframework.kafka.config.TopicBuilder;
@Configuration public class KafkaConfig { @Bean NewTopic orderCreatedTopic(){return TopicBuilder.name("order.created").partitions(6).replicas(1).build();} }
