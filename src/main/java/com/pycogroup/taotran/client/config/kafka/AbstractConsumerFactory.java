package com.pycogroup.taotran.client.config.kafka;

import org.apache.avro.specific.SpecificRecordBase;
import org.apache.kafka.common.serialization.Deserializer;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;

import java.util.Map;

public class AbstractConsumerFactory<K, V extends SpecificRecordBase> extends DefaultKafkaConsumerFactory<K, V> {

    public AbstractConsumerFactory(Map<String, Object> configs, Deserializer<K> keyDeserializer, Deserializer<V> valueDeserializer) {
        super(configs, keyDeserializer, valueDeserializer);
    }
}
