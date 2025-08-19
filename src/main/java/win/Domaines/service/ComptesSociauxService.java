package win.Domaines.service;

import org.springframework.stereotype.Service;
import win.Domaines.dto.ComptesSociauxDTO;
import win.Domaines.entity.ComptesSociaux;
import win.Domaines.exception.ResourceNotFoundException;
import win.Domaines.repository.ComptesSociauxRepository;

import java.util.Base64;
import java.util.List;

@Service
public class ComptesSociauxService {

    private final ComptesSociauxRepository comptesSociauxRepository;

    public ComptesSociauxService(ComptesSociauxRepository comptesSociauxRepository) {
        this.comptesSociauxRepository = comptesSociauxRepository;
    }

    public ComptesSociaux create(ComptesSociauxDTO dto) {
        ComptesSociaux c = new ComptesSociaux();
        c.setNom(dto.getNom());
        c.setLien(dto.getLien());

        if (dto.getIcon() != null && !dto.getIcon().isEmpty()) {
            byte[] iconBytes = Base64.getDecoder().decode(dto.getIcon());
            c.setIcon(iconBytes);
        }

        return comptesSociauxRepository.save(c);
    }

    public ComptesSociaux update(Long id, ComptesSociauxDTO dto) {
        ComptesSociaux c = comptesSociauxRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Compte social not found with id " + id));

        c.setNom(dto.getNom());
        c.setLien(dto.getLien());

        if (dto.getIcon() != null && !dto.getIcon().isEmpty()) {
            byte[] iconBytes = Base64.getDecoder().decode(dto.getIcon());
            c.setIcon(iconBytes);
        }

        return comptesSociauxRepository.save(c);
    }

    public void deleteById(Long id) {
        comptesSociauxRepository.deleteById(id);
    }

    public ComptesSociaux getById(Long id) {
        return comptesSociauxRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Compte social not found with id " + id));
    }

    public List<ComptesSociaux> getAll() {
        return comptesSociauxRepository.findAll();
    }
}
