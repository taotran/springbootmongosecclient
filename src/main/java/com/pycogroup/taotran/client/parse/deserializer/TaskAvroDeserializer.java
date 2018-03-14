package com.pycogroup.taotran.client.parse.deserializer;

import com.pycogroup.taotran.springbootmongosec.avroentity.Task;
import org.springframework.stereotype.Component;

@Component
public class TaskAvroDeserializer extends AvroDeserializer<Task> {

    public TaskAvroDeserializer(Class<Task> targetType) {
        super(targetType);
    }
}
