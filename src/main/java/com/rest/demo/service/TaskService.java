package com.rest.demo.service;

import com.rest.demo.model.Task;
import com.rest.demo.model.TaskStatus;
import com.rest.demo.model.Worker;

import java.util.List;
import java.util.Optional;

public interface TaskService {
    Optional<Task> findTask(Long id);

    List<Task> getAllTasks();

    Task addTask(Task task);

    List<Task> searchTask(String name, Long assigneeId);

    Optional<Task> updateTask(Long id, Task task);

    Optional<Task> updateTaskStatus(Long id, TaskStatus taskStatus);

    //Task updateTaskStatus(Long id, Task task);

    Optional<Task> updateTaskAssignee(Long id, Worker worker);
}
