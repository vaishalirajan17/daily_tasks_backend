package com.rajan.daily_tasks_backend.pojos.responses;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class DailyTaskResponse {
    private int task_sno;
    private String task_subject;
    private String task_time;
    private String task_remarks;
}
