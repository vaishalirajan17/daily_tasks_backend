package com.rajan.daily_tasks_backend.controllers;

import com.rajan.daily_tasks_backend.entities.DailyTaskEntity;
import com.rajan.daily_tasks_backend.pojos.requests.DailyTaskRequest;
import com.rajan.daily_tasks_backend.pojos.responses.DailyTaskResponse;
import com.rajan.daily_tasks_backend.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
public class TaskController {

    @Autowired
    TaskService taskService;

    @GetMapping("/tasks")
    public List<DailyTaskEntity> getDailyTasks() {
        return taskService.getTasks();
    }

    @PostMapping("/create")
    public void createTask(@RequestBody DailyTaskRequest dailyTaskRequest) {
        System.out.println(dailyTaskRequest.toString());
        taskService.createTasks(dailyTaskRequest);
    }

}
