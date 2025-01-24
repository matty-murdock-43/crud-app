package com.rest.demo.dto;

import com.rest.demo.model.Worker;

import java.util.Objects;

public record WorkerDto(Long id, String fName, String lName, String email) {
    public static class Mapper{
        public static Worker toModel(WorkerDto workerDto){
            if(workerDto == null) return null;
            Worker model = new Worker(workerDto.email(), workerDto.fName(), workerDto.lName());
            if(!Objects.isNull(workerDto.id())){
                model.setId(workerDto.id());
            }
            return model;
        }
        public static WorkerDto toDto(Worker model){
            if(model == null) return null;
            WorkerDto workerDto = new WorkerDto(model.getId(), model.getfName(), model.getlName(), model.getEmail()); // WorkerDto is an immutable record
            return workerDto;
        }
    }
}
