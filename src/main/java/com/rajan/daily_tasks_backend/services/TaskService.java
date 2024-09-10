package com.rajan.daily_tasks_backend.services;

import com.rajan.daily_tasks_backend.entities.DailyTaskEntity;
import com.rajan.daily_tasks_backend.pojos.requests.DailyTaskRequest;
import com.rajan.daily_tasks_backend.pojos.responses.DailyTaskResponse;
import com.rajan.daily_tasks_backend.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import java.util.Date;
import java.text.SimpleDateFormat;

@Service
public class TaskService {

    @Autowired
    TaskRepository taskRepository;

    public List<DailyTaskResponse> getTasks(int tasks_date_num) {
        List<DailyTaskEntity> dailyTaskEntities = new ArrayList<>();
        if(tasks_date_num == 0) {
            dailyTaskEntities=taskRepository.findTasks();
        } else if (tasks_date_num == -1) {
            dailyTaskEntities=taskRepository.findTasksPrev();
        } else if (tasks_date_num == 1) {
            dailyTaskEntities=taskRepository.findTasksNext();
        }
        List<DailyTaskResponse> dailyTaskResponses = new ArrayList<>();
        for(int i = 0 ; i < dailyTaskEntities.size();i++) {

            DailyTaskResponse dailyTaskResponse = new DailyTaskResponse();
            dailyTaskResponse.setTask_sno((int) dailyTaskEntities.get(i).getTask_id());
            dailyTaskResponse.setTask_subject(dailyTaskEntities.get(i).getTask_subject());
            dailyTaskResponse.setTask_remarks(dailyTaskEntities.get(i).getTask_remarks());

            SimpleDateFormat localDateFormat = new SimpleDateFormat("HH:mm:ss");
            String time = localDateFormat.format(dailyTaskEntities.get(i).getTask_time());

            dailyTaskResponse.setTask_time(time);
            System.out.println(dailyTaskResponse);

            dailyTaskResponses.add(dailyTaskResponse);
        }

        return dailyTaskResponses;
    }

    public void createTasks(DailyTaskRequest dailyTaskRequest) {

        DailyTaskEntity dailyTaskEntity = new DailyTaskEntity();

        dailyTaskEntity.setTask_subject(dailyTaskRequest.getTask_subject());
        dailyTaskEntity.setTask_remarks(dailyTaskRequest.getTask_remarks());
        dailyTaskEntity.setTask_time(dailyTaskRequest.getTask_time());

        taskRepository.save(dailyTaskEntity);
    }

    public List<DailyTaskResponse> getTasksEdit(String gvnDate) {

        List<DailyTaskResponse> dailyTaskResponses = new ArrayList<>();

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            // Parse the string into a Date object
            Date date = dateFormat.parse(gvnDate);
            System.out.println("Parsed Date: " + date);
            List<DailyTaskEntity> dailyTaskEntities = taskRepository.findTasksGvnDate(date);
            for(int i = 0 ; i < dailyTaskEntities.size();i++) {

                DailyTaskResponse dailyTaskResponse = new DailyTaskResponse();
                dailyTaskResponse.setTask_sno((int) dailyTaskEntities.get(i).getTask_id());
                dailyTaskResponse.setTask_subject(dailyTaskEntities.get(i).getTask_subject());
                dailyTaskResponse.setTask_remarks(dailyTaskEntities.get(i).getTask_remarks());

                SimpleDateFormat localDateFormat = new SimpleDateFormat("HH:mm:ss");
                String time = localDateFormat.format(dailyTaskEntities.get(i).getTask_time());

                dailyTaskResponse.setTask_time(time);
                System.out.println(dailyTaskResponse);

                dailyTaskResponses.add(dailyTaskResponse);
            }
        } catch (ParseException e) {
            System.out.println("Date parsing failed: " + e.getMessage());
        }

        System.out.println(dailyTaskResponses.size());
        return dailyTaskResponses;
    }
}
