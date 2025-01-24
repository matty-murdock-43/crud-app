package com.rest.demo.controller;

import com.rest.demo.dto.TaskDto;
import com.rest.demo.dto.WorkerDto;
import com.rest.demo.model.Worker;
import com.rest.demo.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import com.rest.demo.model.Task;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/tasks")
public class TaskController {
    @Autowired
    TaskService taskService;

//    @GetMapping(value = "/allTasks")
//    public List<TaskDto> tasks() {
//        List<Task> tasks = taskService.getAllTasks();
////        List<TaskDto> tasksDto = new ArrayList<>();
////        for (Task task : tasks) {
////            TaskDto taskDto = TaskDto.Mapper.toDto(task);
////            tasksDto.add(taskDto);
////        }
//        List<TaskDto> tasksDto = tasks.stream().map(TaskDto.Mapper::toDto).collect(Collectors.toList());
//        return tasksDto;
//    }

    @GetMapping(value = "/{id}")
    public TaskDto findOne(@PathVariable Long id){
        Task task = taskService.findTask(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return  TaskDto.Mapper.toDto(task);
    }

    @GetMapping
    public List<TaskDto> searchTasks(@RequestBody(required = false) String name, @RequestBody(required = false) Long assigneeId) {
        List<Task> tasks = taskService.searchTask(name, assigneeId);
        List<TaskDto> tasksDto = tasks.stream().map(TaskDto.Mapper::toDto).collect(Collectors.toList());
        return tasksDto;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TaskDto addTaskDto(@RequestBody TaskDto taskDto){
        Task task = TaskDto.Mapper.toModel(taskDto);
        return TaskDto.Mapper.toDto(taskService.addTask(task));
    }

    @PutMapping(value = "/{id}")
    public TaskDto updateTaskDto(@PathVariable Long id, @RequestBody TaskDto taskDto){
        Task task = TaskDto.Mapper.toModel(taskDto);
        Task updatedTask = taskService.updateTask(id, task).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)); //add orElseThrow
        return TaskDto.Mapper.toDto(updatedTask);
    }

    @PutMapping(value = "/{id}/status")
    public TaskDto updateTaskStatus(@PathVariable Long id, @RequestBody TaskDto updatedTask){
//        Task task = TaskDto.Mapper.toModel(updatedTask);
//        Task updateStatus = taskService.updateTaskStatus(id, updatedTask.taskStatus());
        Task updateStatus = taskService.updateTaskStatus(id, updatedTask.taskStatus()).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND));
        return TaskDto.Mapper.toDto(updateStatus);
    }

    @PutMapping(value = "/{id}/assignee")
    public TaskDto updateAssignee(@PathVariable Long id, @RequestBody TaskDto updatedTask){
//        Task task = TaskDto.Mapper.toModel(updatedTask);
//        Task updateAssignee = taskService.updateTaskAssignee(id, updatedTask.workerDto());
        Task updateAssignee = taskService.updateTaskAssignee(id, WorkerDto.Mapper.toModel(updatedTask.workerDto())).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND));
        return TaskDto.Mapper.toDto(updateAssignee);
    }
}
