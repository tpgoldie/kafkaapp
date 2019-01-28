package com.tpg.kafka.app;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

public class KafkaMessagePublisher implements MessageSender {

    private final MessageChannelConfiguration configuration;
    private final MessageChannel messageChannel;

    public KafkaMessagePublisher(MessageChannelConfiguration configuration) {

        this(configuration, new KafkaProducer<String, String>(buildProperties(configuration)));
    }

    private KafkaMessagePublisher(MessageChannelConfiguration configuration, final KafkaProducer<String, String> kafkaProducer) {

        this(configuration, new MessageChannel() {

            public void send(ProducerRecord<String, String> record) {
                kafkaProducer.send(record);
            }
        });
    }

    private static Properties buildProperties(MessageChannelConfiguration configuration) {

        Properties properties = new Properties();

        properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, configuration.getBootstrapServers());
        properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, configuration.getKeySerializer());
        properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, configuration.getValueSerializer());

        return properties;
    }

    KafkaMessagePublisher(MessageChannelConfiguration configuration, MessageChannel messageChannel) {

        this.configuration = configuration;
        this.messageChannel = messageChannel;
    }

    public void send(String msg) {

        ProducerRecord<String, String> record = new ProducerRecord<String, String>(configuration.getTopic(), msg);

        messageChannel.send(record);
    }
}
