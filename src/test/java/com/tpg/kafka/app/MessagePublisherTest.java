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
    private MessageProducer messageProducer;

    @InjectMocks
    private MessagePublisher messagePublisher;

    @Test
    public void sendMessage() {

        final String msg = "Hello World";

        given()
            .topic("demo-1")
            .messageProducer(messageProducer)
        .when()
            .sendMessage(msg)
        .then()
            .messageSent(msg);
    }
}
