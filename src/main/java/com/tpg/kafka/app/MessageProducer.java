package com.tpg.kafka.app;

public interface MessageProducer {
    void send(String topic, String msg);
}
