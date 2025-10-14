package org.example.recrutement.services;

import org.example.recrutement.entities.Offre;

import java.util.List;

public interface OffreService {
    public Offre createOffre(Offre offre);
    public Offre updateOffre(Offre offre);
    public void deleteOffre(Long offre);
    public List<Offre> listOffre();
    public List <Offre> listOffreByUser(Long id);
    public List <Offre> listOffreByStatus(String status);
    public Offre changeStatus(Long id);
}
