package com.rajan.daily_tasks_backend.pojos.requests;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Date;


@Getter
@Setter
@ToString
public class DailyTaskRequest {

    private String task_subject;
    private String task_time;
    private String task_remarks;

}
