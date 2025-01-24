package com.rest.demo.service.serviceImpl;

import com.rest.demo.model.Worker;
import com.rest.demo.repo.WorkerRepo;
import com.rest.demo.service.WokrerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WorkerServiceImpl implements WokrerService {
    @Autowired
    WorkerRepo workerRepo;

    @Override
    public Optional<Worker> findById(Long id) {
        return workerRepo.findById(id);
    }

    @Override
    public Worker createWorker(Worker model) {
        model.setId(null);
        return workerRepo.save(model);
    }

    @Override
    public Optional<Worker> updateWorker(Long id, Worker createdWorker) {
        return workerRepo.findById(id).map(oldWorker -> update(oldWorker,createdWorker)).map(workerRepo::save);
    }

    @Override
    public List<Worker> getAllWorkers() {
        return workerRepo.findAll();
    }

    private Worker update(Worker oldWorker, Worker createdWorker) {
        oldWorker.setfName(createdWorker.getfName());
        oldWorker.setlName(createdWorker.getlName());
        return oldWorker;
    }

}
