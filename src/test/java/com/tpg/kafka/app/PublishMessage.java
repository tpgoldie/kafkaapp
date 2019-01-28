package com.tpg.kafka.app;

import org.apache.kafka.clients.producer.ProducerRecord;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.verify;

public class PublishMessage {

    public static PublishMessage given() {

        return new PublishMessage();
    }

    public PublishMessage bootstrapServers(String value) {

        bootstrapServers = value;
        return this;
    }

    public PublishMessage keySerializer(String value) {

        keySerializer = value;
        return this;
    }

    public PublishMessage valueSerializer(String value) {

        valueSerializer = value;
        return this;
    }

    PublishMessage topic(String value) {

        topic = value;
        return this;
    }

    PublishMessage configuration(MessageChannelConfiguration value) {

        this.configuration = value;

        return this;
    }

    PublishMessage messageChannel(MessageChannel value) {

        this.messageChannel = value;

        return this;
    }

    PublishMessage when() { return this; }

    PublishMessage publishMessage(String value) {

        Mockito.when(configuration.getBootstrapServers()).thenReturn(bootstrapServers);
        Mockito.when(configuration.getKeySerializer()).thenReturn(keySerializer);
        Mockito.when(configuration.getValueSerializer()).thenReturn(valueSerializer);
        Mockito.when(configuration.getTopic()).thenReturn(topic);

        MessageSender sender = new KafkaMessagePublisher(configuration, messageChannel);

        sender.send(value);

        return this;
    }

    PublishMessage then() { return this; }

    PublishMessage messagePublished(String value) {

        ArgumentCaptor<ProducerRecord> argumentCaptor = ArgumentCaptor.forClass(ProducerRecord.class);

        verify(messageChannel).send(argumentCaptor.capture());

        ProducerRecord actual = argumentCaptor.getValue();

        assertThat((String) actual.value(), is(value));

        return configurationUsed();
    }

    private PublishMessage configurationUsed() {
        verify(configuration).getBootstrapServers();
        verify(configuration).getKeySerializer();
        verify(configuration).getValueSerializer();
        verify(configuration).getTopic();

        return this;
    }

    private PublishMessage() {}

    private String bootstrapServers;

    private String keySerializer;

    private String valueSerializer;

    private String topic;

    private MessageChannelConfiguration configuration;

    private MessageChannel messageChannel;
}
