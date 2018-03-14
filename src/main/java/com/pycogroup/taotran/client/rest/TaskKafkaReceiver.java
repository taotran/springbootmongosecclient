package com.pycogroup.taotran.client.rest;

import com.pycogroup.taotran.client.entity.TaskDTO;
import com.pycogroup.taotran.client.service.task.TaskService;
import com.pycogroup.taotran.springbootmongosec.avroentity.Task;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class TaskKafkaReceiver extends BaseKafkaReceiver<TaskDTO, Task> {

    private static final Logger LOGGER = LoggerFactory.getLogger(TaskKafkaReceiver.class);

    @Autowired
    private TaskService taskService;

    @KafkaListener(topics = "${kafka.topic.task}")
    @Override
    public void onReceive(Task task) {
        super.onReceive(task);

        final TaskDTO taskDTO = new TaskDTO(task);

        final TaskDTO result = taskService.save(taskDTO);

        if (result != null) {
            LOGGER.debug("Successfully persisted receiving task '{}'", result);
        }


    }
}
