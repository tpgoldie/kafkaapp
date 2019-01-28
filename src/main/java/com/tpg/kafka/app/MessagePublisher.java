package com.tpg.kafka.app;

public class MessagePublisher {

    private final ProducerConfiguration configuration;
    private final MessageProducer messageProducer;

    MessagePublisher(ProducerConfiguration configuration, MessageProducer messageProducer) {

        this.configuration = configuration;
        this.messageProducer = messageProducer;
    }

    public void send(String msg) {
        messageProducer.send(configuration.getTopic(), msg);
    }
}
