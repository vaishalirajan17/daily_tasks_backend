package com.rajan.daily_tasks_backend.pojos.requests;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
public class DailyTaskRequest {

    private String task_subject;
    private Date task_time;
    private String task_remarks;

}
