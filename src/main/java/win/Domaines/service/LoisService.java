package win.Domaines.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import win.Domaines.dto.LoisDTO;
import win.Domaines.entity.Lois;
import win.Domaines.repository.LoisRepository;

import java.util.Base64;
import java.util.List;

@Service
public class LoisService {

    private final LoisRepository repository;

    public LoisService(LoisRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public Lois create(LoisDTO dto) {
        Lois l = new Lois();
        l.setTitrearabe(dto.getTitrearabe());
        l.setTitrefrancais(dto.getTitrefrancais());
        l.setDatesortie(dto.getDatesortie());

        if (dto.getFichierarabe() != null && !dto.getFichierarabe().isEmpty()) {
            l.setFichierarabe(Base64.getDecoder().decode(dto.getFichierarabe()));
        }
        if (dto.getFichierfrancais() != null && !dto.getFichierfrancais().isEmpty()) {
            l.setFichierfrancais(Base64.getDecoder().decode(dto.getFichierfrancais()));
        }

        return repository.save(l);
    }

    @Transactional
    public Lois update(Long id, LoisDTO dto) {
        Lois l = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Lois non trouvée"));

        l.setTitrearabe(dto.getTitrearabe());
        l.setTitrefrancais(dto.getTitrefrancais());
        l.setDatesortie(dto.getDatesortie());

        if (dto.getFichierarabe() != null && !dto.getFichierarabe().isEmpty()) {
            l.setFichierarabe(Base64.getDecoder().decode(dto.getFichierarabe()));
        }
        if (dto.getFichierfrancais() != null && !dto.getFichierfrancais().isEmpty()) {
            l.setFichierfrancais(Base64.getDecoder().decode(dto.getFichierfrancais()));
        }

        return repository.save(l);
    }

    @Transactional
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public Lois getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Lois non trouvée"));
    }

    @Transactional(readOnly = true)
    public List<Lois> getAll() {
        return repository.findAll();
    }
}
