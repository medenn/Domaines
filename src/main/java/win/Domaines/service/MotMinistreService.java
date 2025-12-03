package win.Domaines.service;

import org.springframework.stereotype.Service;
import win.Domaines.dto.MotMinistreDTO;
import win.Domaines.entity.MotMinistre;
import win.Domaines.exception.ResourceNotFoundException;
import win.Domaines.repository.MotMinistreRepository;

import java.util.List;

@Service
public class MotMinistreService {


private final MotMinistreRepository repository;

public MotMinistreService(MotMinistreRepository repository) {
    this.repository = repository;
}

public MotMinistre create(MotMinistreDTO dto) {
    MotMinistre m = new MotMinistre();
    m.setTitrearabe(dto.getTitrearabe());
    m.setTitrefrancais(dto.getTitrefrancais());
    m.setTextarabe(dto.getTextarabe());
    m.setTextfrancais(dto.getTextfrancais());
    return repository.save(m);
}

public MotMinistre update(Long id, MotMinistreDTO dto) {
    MotMinistre m = repository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("MotMinistre not found"));

    m.setTitrearabe(dto.getTitrearabe());
    m.setTitrefrancais(dto.getTitrefrancais());
    m.setTextarabe(dto.getTextarabe());
    m.setTextfrancais(dto.getTextfrancais());
    return repository.save(m);
}

public void deleteById(Long id) {
    repository.deleteById(id);
}

public MotMinistre getById(Long id) {
    return repository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("MotMinistre not found"));
}

public List<MotMinistre> getAll() {
    return repository.findAll();
}


}
