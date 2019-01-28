package com.tpg.kafka.app;

import org.apache.kafka.clients.producer.ProducerRecord;

public interface MessageChannel {
    void send(ProducerRecord<String, String> record);
}
