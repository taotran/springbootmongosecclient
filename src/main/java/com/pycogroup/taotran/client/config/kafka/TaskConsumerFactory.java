package com.pycogroup.taotran.client.config.kafka;

import com.pycogroup.taotran.client.parse.deserializer.AvroDeserializer;
import com.pycogroup.taotran.springbootmongosec.avroentity.Task;
import org.apache.kafka.common.serialization.Deserializer;
import org.springframework.stereotype.Component;

import java.util.Map;

//@Component
public class TaskConsumerFactory extends AbstractConsumerFactory<String, Task> {

    public TaskConsumerFactory(Map<String, Object> configs, Deserializer<String> keyDeserializer, AvroDeserializer<Task> valueDeserializer) {
        super(configs, keyDeserializer, valueDeserializer);

    }
}
