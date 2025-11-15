package org.example.recrutement.services.impl;

import jakarta.transaction.Transactional;
import org.example.recrutement.entities.Services;
import org.example.recrutement.repositories.ServiceRepository;
import org.example.recrutement.services.ServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class ServiceServiceIml implements ServiceService {
    @Autowired
    ServiceRepository serviceRepo;
    @Override
    public Services saveService(Services service) {
        return serviceRepo.save(service);

    }
    @Override
    public List<Services> listServices() {
        return  serviceRepo.findAll();

    }
    @Override
    public void deleteService(Long id) {
        serviceRepo.deleteById(id);

    }
}
