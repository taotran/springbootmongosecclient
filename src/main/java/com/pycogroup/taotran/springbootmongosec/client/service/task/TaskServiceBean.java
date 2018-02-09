package com.pycogroup.taotran.springbootmongosec.client.service.task;

import com.pycogroup.taotran.springbootmongosec.client.entity.TaskDTO;
import com.pycogroup.taotran.springbootmongosec.client.service.EntityServiceBean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TaskServiceBean extends EntityServiceBean<TaskDTO> implements TaskService {

}
