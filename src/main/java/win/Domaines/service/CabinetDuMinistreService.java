package win.Domaines.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import win.Domaines.dto.CabinetDuMinistreDTO;
import win.Domaines.entity.CabinetDuMinistre;
import win.Domaines.repository.CabinetDuMinistreRepository;

import java.util.List;

@Service
public class CabinetDuMinistreService {

    private final CabinetDuMinistreRepository repository;

    public CabinetDuMinistreService(CabinetDuMinistreRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public CabinetDuMinistre create(CabinetDuMinistreDTO dto) {
        CabinetDuMinistre c = new CabinetDuMinistre();
        c.setTitrearabe(dto.getTitrearabe());
        c.setTitrefrancais(dto.getTitrefrancais());
        c.setDescarabe(dto.getDescarabe());
        c.setDescfrancais(dto.getDescfrancais());
        c.setFichier(dto.getFichier());
        return repository.save(c);
    }

    @Transactional
    public CabinetDuMinistre update(Long id, CabinetDuMinistreDTO dto) {
        CabinetDuMinistre c = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Donnée introuvable"));

        c.setTitrearabe(dto.getTitrearabe());
        c.setTitrefrancais(dto.getTitrefrancais());
        c.setDescarabe(dto.getDescarabe());
        c.setDescfrancais(dto.getDescfrancais());
        c.setFichier(dto.getFichier());

        return repository.save(c);
    }

    @Transactional
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public CabinetDuMinistre getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Donnée introuvable"));
    }

    @Transactional(readOnly = true)
    public List<CabinetDuMinistre> getAll() {
        return repository.findAll();
    }
}
