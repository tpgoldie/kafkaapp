package com.tpg.kafka.app;

import static org.mockito.Mockito.verify;

public class SendMessage {

    static SendMessage given() {

        return new SendMessage();
    }

    SendMessage topic(String value) {

        topic = value;
        return this;
    }

    SendMessage messageProducer(MessageProducer value) {

        this.messageProducer = value;

        return this;
    }

    SendMessage when() { return this; }

    SendMessage sendMessage(String value) {

        MessagePublisher producer = new MessagePublisher(topic, messageProducer);

        producer.send(value);

        return this;
    }

    SendMessage then() { return this; }

    SendMessage messageSent(String value) {

        verify(messageProducer).send(topic, value);

        return this;
    }

    private SendMessage() {}

    private String topic;

    private MessageProducer messageProducer;
}
