package com.rajan.daily_tasks_backend.services;

import com.rajan.daily_tasks_backend.entities.DailyTaskEntity;
import com.rajan.daily_tasks_backend.pojos.requests.DailyTaskRequest;
import com.rajan.daily_tasks_backend.pojos.responses.DailyTaskResponse;
import com.rajan.daily_tasks_backend.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import java.util.Date;
import java.text.SimpleDateFormat;

@Service
public class TaskService {

    @Autowired
    TaskRepository taskRepository;

    public List<DailyTaskResponse> getTasks() {
        List<DailyTaskEntity> dailyTaskEntities = new ArrayList<>();
        dailyTaskEntities = taskRepository.findTasks();
        List<DailyTaskResponse> dailyTaskResponses = new ArrayList<>();
        for (DailyTaskEntity dailyTaskEntity : dailyTaskEntities) {

            DailyTaskResponse dailyTaskResponse = new DailyTaskResponse();
            dailyTaskResponse.setTask_sno((int) dailyTaskEntity.getTask_id());
            dailyTaskResponse.setTask_subject(dailyTaskEntity.getTask_subject());
            dailyTaskResponse.setTask_time(String.valueOf(dailyTaskEntity.getTask_time()));
            dailyTaskResponse.setTask_remarks(dailyTaskEntity.getTask_remarks());

            dailyTaskResponses.add(dailyTaskResponse);
        }

        return dailyTaskResponses;
    }

    public void createTasks(DailyTaskRequest dailyTaskRequest) {

        DailyTaskEntity dailyTaskEntity = new DailyTaskEntity();

        dailyTaskEntity.setTask_subject(dailyTaskRequest.getTask_subject());
        dailyTaskEntity.setTask_remarks(dailyTaskRequest.getTask_remarks());
        dailyTaskEntity.setTask_time(convert(dailyTaskRequest.getTask_time()));

        taskRepository.save(dailyTaskEntity);
    }

    public java.sql.Timestamp convert(String ts) {
        ts = ts.replace("T", " ").concat(":00");
        System.out.println(ts);
        return Timestamp.valueOf(ts);
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

    public String deleteTask(Long task_id) {

        try {
            taskRepository.deleteById(task_id);
        }
        catch (Exception e) {
            return "failed";
        }
     return "deleted";
    }
}
