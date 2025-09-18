package win.Domaines.service;

import org.springframework.stereotype.Service;
import win.Domaines.dto.StructureAdministrativeDTO;
import win.Domaines.entity.StructureAdministrative;
import win.Domaines.exception.ResourceNotFoundException;
import win.Domaines.repository.StructureAdministrativeRepository;

import java.util.Base64;
import java.util.List;

@Service
public class StructureAdministrativeService {

    private final StructureAdministrativeRepository repository;

    public StructureAdministrativeService(StructureAdministrativeRepository repository) {
        this.repository = repository;
    }

    public StructureAdministrative create(StructureAdministrativeDTO dto) {
        StructureAdministrative s = new StructureAdministrative();
        s.setTitrearabe(dto.getTitrearabe());
        s.setTitrefrancais(dto.getTitrefrancais());
        s.setTextArabe(dto.getTextarabe());
        s.setTextFrancais(dto.getTextfrancais());

        if (dto.getFichierarabe() != null) {
            s.setFichierarabe(Base64.getDecoder().decode(dto.getFichierarabe()));
        }
        if (dto.getFichierfrancais() != null) {
            s.setFichierfrancais(Base64.getDecoder().decode(dto.getFichierfrancais()));
        }

        return repository.save(s);
    }

    public StructureAdministrative update(Long id, StructureAdministrativeDTO dto) {
        StructureAdministrative s = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("StructureAdministrative not found"));

        s.setTitrearabe(dto.getTitrearabe());
        s.setTitrefrancais(dto.getTitrefrancais());
        s.setTextArabe(dto.getTextarabe());
        s.setTextFrancais(dto.getTextfrancais());

        if (dto.getFichierarabe() != null) {
            s.setFichierarabe(Base64.getDecoder().decode(dto.getFichierarabe()));
        }
        if (dto.getFichierfrancais() != null) {
            s.setFichierfrancais(Base64.getDecoder().decode(dto.getFichierfrancais()));
        }

        return repository.save(s);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    public StructureAdministrative getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("StructureAdministrative not found"));
    }

    public List<StructureAdministrative> getAll() {
        return repository.findAll();
    }
}
