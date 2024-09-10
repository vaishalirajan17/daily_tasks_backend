package com.rajan.daily_tasks_backend.repositories;

import com.rajan.daily_tasks_backend.entities.DailyTaskEntity;
import com.rajan.daily_tasks_backend.pojos.responses.DailyTaskResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<DailyTaskEntity,Long> {

    @Query(value="select task_id,task_subject, task_time, task_remarks from daily_tasks where CONVERT(date,task_time)=CONVERT(date,GETDATE())", nativeQuery = true)
    List<DailyTaskEntity> findTasks();

    @Query(value="select task_id,task_subject, task_time, task_remarks from daily_tasks where CONVERT(date,task_time)=CONVERT(date,GETDATE()-1)", nativeQuery = true)
    List<DailyTaskEntity> findTasksPrev();

    @Query(value="select task_id,task_subject, task_time, task_remarks from daily_tasks where CONVERT(date,task_time)=CONVERT(date,GETDATE()+1)", nativeQuery = true)
    List<DailyTaskEntity> findTasksNext();

    @Query(value="select task_id,task_subject, task_time, task_remarks from daily_tasks where CONVERT(date,task_time)=CONVERT(date,?1)", nativeQuery = true)
    List<DailyTaskEntity> findTasksGvnDate(Date gvnDate);
}
