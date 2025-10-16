package win.Domaines.service;

import org.springframework.stereotype.Service;
import win.Domaines.dto.MinistereDTO;
import win.Domaines.entity.Ministere;
import win.Domaines.exception.ResourceNotFoundException;
import win.Domaines.repository.MinistereRepository;

import java.util.Base64;
import java.util.List;

@Service
public class MinistereService {

    private final MinistereRepository ministreRepository;

    public MinistereService(MinistereRepository ministreRepository) {
        this.ministreRepository = ministreRepository;
    }

    public Ministere create(MinistereDTO dto) {
        Ministere m = new Ministere();
        m.setTitrearabe(dto.getTitrearabe());
        m.setTitrefrancais(dto.getTitrefrancais());
        m.setTextarabe(dto.getTextarabe());
        m.setTextfrancais(dto.getTextfrancais());

        if (dto.getImage() != null && !dto.getImage().isEmpty()) {
            byte[] imageBytes = Base64.getDecoder().decode(dto.getImage());
            m.setImage(imageBytes);
        }

        return ministreRepository.save(m);
    }

    public Ministere update(Long id, MinistereDTO dto) {
        Ministere m = ministreRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Ministre not found"));

        m.setTitrearabe(dto.getTitrearabe());
        m.setTitrefrancais(dto.getTitrefrancais());
        m.setTextarabe(dto.getTextarabe());
        m.setTextfrancais(dto.getTextfrancais());

        if (dto.getImage() != null && !dto.getImage().isEmpty()) {
            byte[] imageBytes = Base64.getDecoder().decode(dto.getImage());
            m.setImage(imageBytes);
        }

        return ministreRepository.save(m);
    }

    public void deleteById(Long id) {
        ministreRepository.deleteById(id);
    }

    public Ministere getById(Long id) {
        return ministreRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Ministre not found with id " + id));
    }

    public List<Ministere> getAll() {
        return ministreRepository.findAll();
    }
}
