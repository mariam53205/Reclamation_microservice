package com.example.reclamation.Services;

import com.example.reclamation.Repositories.ReclamationRepository;
import com.example.reclamation.entities.Reclamation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReclamationService implements IReclamationService {

    private final ReclamationRepository reclamationRepository;

    @Autowired
    public ReclamationService(ReclamationRepository reclamationRepository) {
        this.reclamationRepository = reclamationRepository;
    }

    @Override
    public Reclamation addReclamation(Reclamation reclamation) {
        return reclamationRepository.save(reclamation);
    }

    @Override
    public Reclamation updateReclamation(Long id, Reclamation reclamation) {
        Optional<Reclamation> existing = reclamationRepository.findById(id);
        if (existing.isPresent()) {
            Reclamation r = existing.get();
            r.setLieuDepart(reclamation.getLieuDepart());
            r.setLieuArrivee(reclamation.getLieuArrivee());
            r.setHeureDepart(reclamation.getHeureDepart());
            r.setHeureArrivee(reclamation.getHeureArrivee());
            r.setDescription(reclamation.getDescription());
            r.setStatut(reclamation.getStatut());
            return reclamationRepository.save(r);
        }
        return null;
    }

    @Override
    public void deleteReclamation(Long id) {
        reclamationRepository.deleteById(id);
    }

    @Override
    public Reclamation getReclamationById(Long id) {
        return reclamationRepository.findById(id).orElse(null);
    }

    @Override
    public List<Reclamation> getAllReclamations() {
        return reclamationRepository.findAll();
    }

    @Override
    public List<Reclamation> getReclamationsByStatut(String statut) {
        return reclamationRepository.findByStatut(statut);
    }
}
