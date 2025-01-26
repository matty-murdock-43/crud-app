package com.rest.demo.service.serviceImpl;

import com.rest.demo.model.Task;
import com.rest.demo.model.TaskStatus;
import com.rest.demo.model.Worker;
import com.rest.demo.repo.TaskRepo;
import com.rest.demo.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskServiceImpl implements TaskService {
    @Autowired
    TaskRepo taskRepo;
    @Override
    public Optional<Task> findTask(Long id) {
        return taskRepo.findById(id);
    }

    @Override
    public List<Task> getAllTasks() {
        return taskRepo.findAll();
    }

    @Override
    public Task addTask(Task task) {
        task.setId(null);
        task.setTaskStatus(TaskStatus.TO_DO);
        task.setAssignee(null);
        return taskRepo.save(task);
    }

    @Override
    public List<Task> searchTask(String name, Long assigneeId) {
        System.out.println("Name from service impl: "+name+" Assignee Id service impl: "+assigneeId );
        return taskRepo.findByNameAndAssignee(name != null ? name : "", assigneeId);
    }

    @Override
    public Optional<Task> updateTask(Long id, Task task) {
        return taskRepo.findById(id).map(base -> updateFields(base, task)).map(taskRepo::save);
        //return null;
    }

    private Task updateFields(Task base, Task task) {
        base.setName(task.getName());
        base.setAssignee(task.getAssignee());
        base.setTaskStatus(task.getTaskStatus());
        base.setCampaign(task.getCampaign());
        base.setDueDate(task.getDueDate());
        base.setDescription(task.getDescription());
        return base;
    }

    @Override
    public Optional<Task> updateTaskStatus(Long id, TaskStatus taskStatus) {
        return taskRepo.findById(id).map(base -> {base.setTaskStatus(taskStatus);return taskRepo.save(base);});

    }

    @Override
    public Optional<Task> updateTaskAssignee(Long id, Worker worker) {
        return taskRepo.findById(id).map(base -> {base.setAssignee(worker); return taskRepo.save(base);});
    }
}
