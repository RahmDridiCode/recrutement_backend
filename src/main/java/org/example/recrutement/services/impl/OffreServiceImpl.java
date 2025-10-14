package org.example.recrutement.services.impl;

import org.example.recrutement.entities.Offre;
import org.example.recrutement.repositories.OffreRepository;
import org.example.recrutement.services.OffreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OffreServiceImpl implements OffreService {
    @Autowired
    private OffreRepository offreRepository;
    @Override
    public Offre createOffre(Offre offre) {
        return offreRepository.save(offre);
    }

    @Override
    public Offre updateOffre(Offre offre) {
        return offreRepository.findById(offre.getId())
                .map(existingOffre -> offreRepository.save(existingOffre))
                .orElseThrow(() -> new RuntimeException("Offre non trouvée"));
    }

    @Override
    public void deleteOffre(Long offreId) {
        if (offreRepository.existsById(offreId)) {
            offreRepository.deleteById(offreId);
        } else {
            throw new RuntimeException("Offre non trouvée");
        }
    }

    @Override
    public List<Offre> listOffre() {
        return offreRepository.findAll();
    }

    @Override
    public List<Offre> listOffreByUser(Long userId) {
        return offreRepository.findByUser_Id(userId);
    }

    @Override
    public List<Offre> listOffreByStatus(String status) {
        return offreRepository.findByStatus(status);
    }

    @Override
    public Offre changeStatus(Long id) {
        return offreRepository.findById(id)
                .map(offre -> {offre.setStatus("closed");
                    return offreRepository.save(offre);
                })
                .orElseThrow(() -> new RuntimeException("Offre non trouvée"));
    }
}
