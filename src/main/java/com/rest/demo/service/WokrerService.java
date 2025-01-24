package com.rest.demo.service;

import com.rest.demo.model.Worker;

import java.util.List;
import java.util.Optional;


public interface WokrerService {
    Optional<Worker> findById(Long id);

    Worker createWorker(Worker model);

    Optional<Worker> updateWorker(Long id, Worker createdWorker);

    List<Worker> getAllWorkers();
}
