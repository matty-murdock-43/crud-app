package com.rest.demo.repo;

import com.rest.demo.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepo extends JpaRepository<Task, Long> {
    @Query("SELECT t FROM Task t WHERE (:name IS NULL or t.name like %:name%) and (:assignee IS NULL or t.assignee = :assignee)")
    List<Task> findByNameAndAssignee(@Param(value = "name") String name, @Param(value = "assignee") Long assigneeId);
}
