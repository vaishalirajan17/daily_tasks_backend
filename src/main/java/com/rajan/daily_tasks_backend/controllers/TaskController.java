package com.rajan.daily_tasks_backend.controllers;

import com.rajan.daily_tasks_backend.entities.DailyTaskEntity;
import com.rajan.daily_tasks_backend.pojos.requests.DailyTaskRequest;
import com.rajan.daily_tasks_backend.pojos.responses.DailyTaskResponse;
import com.rajan.daily_tasks_backend.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin("*")
public class TaskController {

    @Autowired
    TaskService taskService;

    @GetMapping("/tasks")
    public List<DailyTaskResponse> getDailyTasks(@RequestParam(name="task_date_num") int task_date_num) {
        System.out.println(task_date_num);
        return taskService.getTasks(task_date_num);
    }

    @PostMapping("/create")
    public void createTask(@RequestBody DailyTaskRequest dailyTaskRequest) {
        System.out.println(dailyTaskRequest.toString());
        taskService.createTasks(dailyTaskRequest);
    }

    @GetMapping("/edit-tasks")
    public List<DailyTaskResponse> getTasksEdit(@RequestParam(name="task_date")String gvnDate) {
        System.out.println(gvnDate);
        return taskService.getTasksEdit(gvnDate);
    }

    @GetMapping("/check")
    public String check(@RequestParam(name="task_date")String gvnDate){
        System.out.println(gvnDate);
        return "hi";
    }


}
