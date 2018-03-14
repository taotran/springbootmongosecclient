package com.pycogroup.taotran.client.parse.deserializer;

import com.pycogroup.taotran.springbootmongosec.avroentity.User;
import org.springframework.stereotype.Component;

@Component
public class UserAvroDeserializer extends AvroDeserializer<User> {

    public UserAvroDeserializer(Class<User> targetType) {
        super(targetType);
    }
}
