package com.rajan.daily_tasks_backend.services;

import com.rajan.daily_tasks_backend.entities.DailyTaskEntity;
import com.rajan.daily_tasks_backend.pojos.requests.DailyTaskRequest;
import com.rajan.daily_tasks_backend.pojos.responses.DailyTaskResponse;
import com.rajan.daily_tasks_backend.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TaskService {

    @Autowired
    TaskRepository taskRepository;

    public List<DailyTaskEntity> getTasks() {
        return taskRepository.findTasks();
    }

    public void createTasks(DailyTaskRequest dailyTaskRequest) {

        DailyTaskEntity dailyTaskEntity = new DailyTaskEntity();

        dailyTaskEntity.setTask_subject(dailyTaskRequest.getTask_subject());
        dailyTaskEntity.setTask_remarks(dailyTaskRequest.getTask_remarks());
        dailyTaskEntity.setTask_time(dailyTaskRequest.getTask_time());

        taskRepository.save(dailyTaskEntity);
    }
}
