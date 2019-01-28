package com.tpg.kafka.app;

import org.mockito.Mockito;

import static org.mockito.Mockito.verify;

public class SendMessage {

    public static SendMessage given() {

        return new SendMessage();
    }

    public SendMessage bootstrapServers(String value) {

        bootstrapServers = value;
        return this;
    }

    public SendMessage keySerializer(String value) {

        keySerializer = value;
        return this;
    }

    public SendMessage valueSerializer(String value) {

        valueSerializer = value;
        return this;
    }

    SendMessage topic(String value) {

        topic = value;
        return this;
    }

    SendMessage configuration(ProducerConfiguration value) {

        this.configuration = value;

        return this;
    }

    SendMessage messageAdapter(MessageProducer value) {

        this.messageProducer = value;

        return this;
    }

    SendMessage when() { return this; }

    SendMessage sendMessage(String value) {

        Mockito.when(configuration.getTopic()).thenReturn(topic);

        MessagePublisher producer = new MessagePublisher(configuration, messageProducer);

        producer.send(value);

        return this;
    }

    SendMessage then() { return this; }

    SendMessage messageSent(String value) {

        verify(messageProducer).send(topic, value);

        return this;
    }

    private SendMessage() {}

    private String bootstrapServers;

    private String keySerializer;

    private String valueSerializer;

    private String topic;

    private ProducerConfiguration configuration;

    private MessageProducer messageProducer;
}
