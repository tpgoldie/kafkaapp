package com.tpg.kafka.app;

import org.apache.kafka.common.serialization.StringSerializer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static com.tpg.kafka.app.PublishMessage.given;

@RunWith(MockitoJUnitRunner.class)
public class KafkaMessagePublisherTest {

    @Mock
    private MessageChannelConfiguration configuration;

    @Mock
    private MessageChannel messageChannel;


    @Test
    public void publishMessage() {

        final String msg = "Hello World";

        final String serializer = StringSerializer.class.getName();

        given()
            .bootstrapServers("localhost:9092")
            .keySerializer(serializer)
            .valueSerializer(serializer)
            .topic("demo-1")
            .configuration(configuration)
            .messageChannel(messageChannel)
        .when()
            .publishMessage(msg)
        .then()
            .messagePublished(msg);
    }
}
