package win.Domaines.service;

import org.springframework.stereotype.Service;
import win.Domaines.dto.LeMinistreDTO;
import win.Domaines.entity.LeMinistre;
import win.Domaines.exception.ResourceNotFoundException;
import win.Domaines.repository.LeMinistreRepository;

import java.util.Base64;
import java.util.List;

@Service
public class LeMinistreService {

    private final LeMinistreRepository repository;

    public LeMinistreService(LeMinistreRepository repository) {
        this.repository = repository;
    }

    public LeMinistre create(LeMinistreDTO dto) {
        LeMinistre m = new LeMinistre();
        m.setTitrearabe(dto.getTitrearabe());
        m.setTitrefrancais(dto.getTitrefrancais());
        m.setTextarabe(dto.getTextarabe());
        m.setTextfrancais(dto.getTextfrancais());

        if (dto.getImage() != null) {
            m.setImage(Base64.getDecoder().decode(dto.getImage()));
        }
        if (dto.getCvarabe() != null) {
            m.setCvarabe(Base64.getDecoder().decode(dto.getCvarabe()));
        }
        if (dto.getCvfrancais() != null) {
            m.setCvfrancais(Base64.getDecoder().decode(dto.getCvfrancais()));
        }

        return repository.save(m);
    }

    public LeMinistre update(Long id, LeMinistreDTO dto) {
        LeMinistre m = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("LeMinistre not found"));

        m.setTitrearabe(dto.getTitrearabe());
        m.setTitrefrancais(dto.getTitrefrancais());
        m.setTextarabe(dto.getTextarabe());
        m.setTextfrancais(dto.getTextfrancais());

        if (dto.getImage() != null) {
            m.setImage(Base64.getDecoder().decode(dto.getImage()));
        }
        if (dto.getCvarabe() != null) {
            m.setCvarabe(Base64.getDecoder().decode(dto.getCvarabe()));
        }
        if (dto.getCvfrancais() != null) {
            m.setCvfrancais(Base64.getDecoder().decode(dto.getCvfrancais()));
        }

        return repository.save(m);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    public LeMinistre getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("LeMinistre not found"));
    }

    public List<LeMinistre> getAll() {
        return repository.findAll();
    }
}
