package com.tpg.kafka.app;

public class MessagePublisher {

    MessagePublisher(String topic, MessageSender messageSender) {

        this.topic = topic;

        this.messageSender = messageSender;
    }

    public void send(String msg) {

        messageSender.send(msg);
    }

    private final String topic;

    private final MessageSender messageSender;
}
