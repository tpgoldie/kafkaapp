package com.tpg.kafka.app;

import org.apache.kafka.common.serialization.StringSerializer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static com.tpg.kafka.app.SendMessage.given;

@RunWith(MockitoJUnitRunner.class)
public class MessagePublisherTest {
    @Mock
    private ProducerConfiguration configuration;

    @Mock
    private MessageProducer messageProducer;

    @InjectMocks
    private MessagePublisher producer;

    @Test
    public void sendMessage() {

        String serializer = StringSerializer.class.getName();

        given()
            .bootstrapServers("localhost:9092")
            .keySerializer(serializer)
            .valueSerializer(serializer)
            .topic("demo-1")
            .configuration(configuration)
            .messageAdapter(messageProducer)
        .when()
            .sendMessage("Hello World")
        .then()
            .messageSent("Hello World");
    }
}
