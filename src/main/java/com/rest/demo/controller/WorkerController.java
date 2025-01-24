package com.rest.demo.controller;

import com.rest.demo.dto.WorkerDto;
import com.rest.demo.service.WokrerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import com.rest.demo.model.Worker;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/workers")
public class WorkerController {
    @Autowired
    WokrerService wokrerService;

    @GetMapping(value = "/{id}")
    public WorkerDto findOne(@PathVariable Long id){
        Worker model = wokrerService.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return WorkerDto.Mapper.toDto(model);
    }

    @GetMapping(value = "/allWorkers")
    public List<WorkerDto> findAllWorkers(){
        List<Worker> workers = wokrerService.getAllWorkers();
        List<WorkerDto> workersDto = new ArrayList<>();
        for(Worker worker: workers){
            WorkerDto workerDto = new WorkerDto(worker.getId(), worker.getfName(), worker.getlName(), worker.getEmail());
            workersDto.add(workerDto);
        }
        return workersDto;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public WorkerDto createWorker(@RequestBody WorkerDto newWorker){
        Worker model = WorkerDto.Mapper.toModel(newWorker);
        return WorkerDto.Mapper.toDto(wokrerService.createWorker(model));
    }

    @PutMapping(value = "/{id}")
    public WorkerDto updateWorker(@PathVariable Long id, @RequestBody WorkerDto workerDto){
        Worker model = WorkerDto.Mapper.toModel(workerDto);
        Worker updatedWorker = wokrerService.updateWorker(id, model).orElseThrow(() ->new ResponseStatusException(HttpStatus.NOT_FOUND));
        return WorkerDto.Mapper.toDto(updatedWorker);
    }

}
