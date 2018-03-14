package com.pycogroup.taotran.client.parse.deserializer;

import com.pycogroup.taotran.client.parse.config.KafkaAvroDeserializerConfig;

import java.util.HashMap;
import java.util.Map;

class ConfigurationUtils {

    private ConfigurationUtils() {
        throw new AssertionError("you must not instantiate this class");
    }

    /**
     * Enables the use of Specific Avro.
     *
     * @param config the serializer/deserializer/serde configuration
     * @return a copy of the configuration where the use of specific Avro is enabled
     */
    public static Map<String, Object> withSpecificAvroEnabled(final Map<String, ?> config) {
        Map<String, Object> specificAvroEnabledConfig =
                config == null ? new HashMap<String, Object>() : new HashMap<>(config);
        //KafkaAvroDeserializerConfig.SPECIFIC_AVRO_READER_CONFIG
        specificAvroEnabledConfig.put("specific.avro.reader", true);
        return specificAvroEnabledConfig;
    }

}