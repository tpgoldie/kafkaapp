package com.tpg.kafka.app;

public class MessagePublisher {
    private final String topic;

    private final MessageProducer messageProducer;

    MessagePublisher(String topic, MessageProducer messageProducer) {

        this.topic = topic;

        this.messageProducer = messageProducer;
    }

    public void send(String msg) {

        messageProducer.send(topic, msg);
    }
}
