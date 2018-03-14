package com.pycogroup.taotran.client.config.kafka;

import com.pycogroup.taotran.client.parse.deserializer.AvroDeserializer;
import com.pycogroup.taotran.springbootmongosec.avroentity.User;
import org.apache.kafka.common.serialization.Deserializer;
import org.springframework.stereotype.Component;

import java.util.Map;

//@Component
public class UserConsumerFactory extends AbstractConsumerFactory<String, User> {

    public UserConsumerFactory(Map<String, Object> configs, Deserializer<String> keyDeserializer, AvroDeserializer<User> valueDeserializer) {
        super(configs, keyDeserializer, valueDeserializer);
    }
}
