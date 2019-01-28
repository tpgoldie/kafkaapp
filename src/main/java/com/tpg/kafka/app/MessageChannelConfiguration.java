package com.tpg.kafka.app;

public interface MessageChannelConfiguration {

    String getBootstrapServers();

    String getKeySerializer();

    String getValueSerializer();

    String getTopic();
}
