package com.rajan.daily_tasks_backend.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name="daily_tasks")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DailyTaskEntity {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name="task_id")
    private long task_id;

    @Column(name="task_subject")
    private String task_subject;

    @Column(name="task_time")
    private Date task_time;

    @Column(name="task_remarks")
    private String task_remarks;

}
