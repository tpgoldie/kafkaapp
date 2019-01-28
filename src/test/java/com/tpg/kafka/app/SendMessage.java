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

    SendMessage messageProducer(MessageSender value) {

        this.messageSender = value;

        return this;
    }

    SendMessage when() { return this; }

    SendMessage sendMessage(String value) {

        MessagePublisher producer = new MessagePublisher(topic, messageSender);

        producer.send(value);

        return this;
    }

    SendMessage then() { return this; }

    SendMessage messageSent(String value) {

        verify(messageSender).send(value);

        return this;
    }

    private SendMessage() {}

    private String topic;

    private MessageSender messageSender;
}
