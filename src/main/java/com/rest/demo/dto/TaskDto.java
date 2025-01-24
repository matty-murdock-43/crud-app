package com.rest.demo.dto;


import com.rest.demo.model.Campaign;
import com.rest.demo.model.Task;
import com.rest.demo.model.TaskStatus;
import com.rest.demo.model.Worker;

import java.time.LocalDate;
import java.util.Objects;

public record TaskDto(Long id, String uuid, String name, String description, LocalDate dueDate, Long campaignId, WorkerDto workerDto, TaskStatus taskStatus) {
    public static class Mapper{
        public static Task toModel(TaskDto taskDto){
            if(taskDto == null) return null;

            Campaign campaign = new Campaign();
            campaign.setId(taskDto.campaignId());

            Task model = new Task(taskDto.uuid(), taskDto.name(), taskDto.description(), taskDto.taskStatus(), taskDto.dueDate(), campaign, WorkerDto.Mapper.toModel(taskDto.workerDto()));
            if(!Objects.isNull(taskDto.id())){
                model.setId(taskDto.id());
            }
            return model;
        }
        public static TaskDto toDto(Task model){
            if(model == null) return null;
            TaskDto taskDto = new TaskDto(model.getId(), model.getUuid(), model.getName(), model.getDescription(), model.getDueDate(), model.getCampaign().getId(), WorkerDto.Mapper.toDto(model.getAssignee()), model.getTaskStatus()); // WorkerDto is an immutable record
            return taskDto;
        }
    }
}
