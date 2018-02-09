package com.pycogroup.taotran.springbootmongosec.client.rest;

import com.pycogroup.taotran.springbootmongosec.avroentity.Task;
import com.pycogroup.taotran.springbootmongosec.client.entity.TaskDTO;
import com.pycogroup.taotran.springbootmongosec.client.service.task.TaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;

import java.util.concurrent.CountDownLatch;

public class KafkaReceiver {

    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaReceiver.class);

    private CountDownLatch latch = new CountDownLatch(1);

    @Autowired
    private TaskService taskService;

    @KafkaListener(topics = "${kafka.topic.avro}")
    public void receive(Task task) {

        LOGGER.debug("Received Kafka payload = '{}'", task.toString());

        latch.countDown();

        final TaskDTO taskDTO = new TaskDTO(task);

        final TaskDTO result = taskService.save(taskDTO);

        if (result != null) {
            LOGGER.debug("Successfully persisted receiving task '{}'", result.toString());
        }

    }

    public CountDownLatch getLatch() {
        return latch;
    }
}
