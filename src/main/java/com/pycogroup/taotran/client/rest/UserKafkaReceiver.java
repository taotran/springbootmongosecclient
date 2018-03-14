package com.pycogroup.taotran.client.rest;

import com.pycogroup.taotran.client.entity.UserDTO;
import com.pycogroup.taotran.client.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class UserKafkaReceiver extends BaseKafkaReceiver<UserDTO, com.pycogroup.taotran.springbootmongosec.avroentity.User> {

    @Autowired
    private UserService userService;

    @Override
    @KafkaListener(topics = "${kafka.topic.user}")
    public void onReceive(com.pycogroup.taotran.springbootmongosec.avroentity.User receivedObj) {
        super.onReceive(receivedObj);

        UserDTO userDTO = new UserDTO();

        userDTO.setId(receivedObj.getId().toString());

    }
}
