package com.tpg.kafka.app;

public interface ProducerConfiguration {

    String getBootstrapServers();

    String getKeySerializer();

    String getValueSerializer();

    String getTopic();
}
