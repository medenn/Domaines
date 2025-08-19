package win.Domaines.service;

import org.springframework.stereotype.Service;
import win.Domaines.entity.TachesMinistere;
import win.Domaines.exception.ResourceNotFoundException;
import win.Domaines.repository.TachesMinistereRepository;
import win.Domaines.dto.TachesMinistereDTO;

import java.util.List;

@Service
public class TachesMinistereService {

    private final TachesMinistereRepository tachesMinistereRepository;

    public TachesMinistereService(TachesMinistereRepository tachesMinistereRepository) {
        this.tachesMinistereRepository = tachesMinistereRepository;
    }

    public TachesMinistere create(TachesMinistereDTO dto) {
        TachesMinistere t = new TachesMinistere();
        t.setTitrearabe(dto.getTitrearabe());
        t.setTitrefrancais(dto.getTitrefrancais());
        return tachesMinistereRepository.save(t);
    }

    public TachesMinistere update(Long id, TachesMinistereDTO dto) {
        TachesMinistere t = tachesMinistereRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Tâche ministère not found with id " + id));
        t.setTitrearabe(dto.getTitrearabe());
        t.setTitrefrancais(dto.getTitrefrancais());
        return tachesMinistereRepository.save(t);
    }

    public void deleteById(Long id) {
        tachesMinistereRepository.deleteById(id);
    }

    public TachesMinistere getById(Long id) {
        return tachesMinistereRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Tâche ministère not found with id " + id));
    }

    public List<TachesMinistere> getAll() {
        return tachesMinistereRepository.findAll();
    }
}
