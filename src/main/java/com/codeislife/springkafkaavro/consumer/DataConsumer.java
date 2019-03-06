package com.codeislife.springkafkaavro.consumer;

import com.codeislife.springkafkaavro.avro.Address;
import com.codeislife.springkafkaavro.avro.Person;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.config.KafkaListenerEndpointRegistry;
import org.springframework.stereotype.Component;

@Component
public class DataConsumer {
    private static final Logger LOGGER = LoggerFactory.getLogger(DataConsumer.class);
    private final KafkaListenerEndpointRegistry registry;

    @Value("${com.codeislife.springkafkaavro.records}")
    private Integer numRecords = 1;

    public DataConsumer(KafkaListenerEndpointRegistry registry) {
        this.registry = registry;
    }

    @KafkaListener(topics = {"${com.codeislife.springkafkaavro.topic}"})
    public void receive(ConsumerRecord<Person, Address> consumerRecord) {
        Person person = consumerRecord.key();
        Address address = consumerRecord.value();
        numRecords--;
        LOGGER.debug("consuming {}, {}, remaining {}", person, address, numRecords);
        if (numRecords <= 0) {
            registry.stop();
        }
    }
}
