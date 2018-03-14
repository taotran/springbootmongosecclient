package com.pycogroup.taotran.client.parse.deserializer;

import org.apache.kafka.common.serialization.Deserializer;

import java.util.Map;

public class SpecificRecordAvroDeserializer<T extends org.apache.avro.specific.SpecificRecord>
        implements Deserializer<T> {

    private final KafkaAvroDeserializer inner;

    public SpecificAvroDeserializer() {
        inner = new KafkaAvroDeserializer();
    }

    /**
     * For testing purposes only.
     */
    SpecificAvroDeserializer(final SchemaRegistryClient client) {
        inner = new KafkaAvroDeserializer(client);
    }

    @Override
    public void configure(final Map<String, ?> deserializerConfig,
                          final boolean isDeserializerForRecordKeys) {
        inner.configure(
                ConfigurationUtils.withSpecificAvroEnabled(deserializerConfig),
                isDeserializerForRecordKeys);
    }

    @SuppressWarnings("unchecked")
    @Override
    public T deserialize(final String topic, final byte[] bytes) {
        return (T) inner.deserialize(topic, bytes);
    }

    @Override
    public void close() {
        inner.close();
    }
}