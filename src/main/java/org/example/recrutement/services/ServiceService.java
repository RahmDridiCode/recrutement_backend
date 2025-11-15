package org.example.recrutement.services;

import java.util.List;
import org.example.recrutement.entities.Services;

public interface ServiceService {
    public Services saveService(Services service);
    public List<Services> listServices();
    public void deleteService(Long id);

}
