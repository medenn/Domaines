package win.Domaines.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import win.Domaines.dto.SousEtablissementsDTO;
import win.Domaines.entity.SousEtablissements;
import win.Domaines.repository.SousEtablissementsRepository;

import java.util.List;

@Service
public class SousEtablissementsService {

    private final SousEtablissementsRepository repository;

    public SousEtablissementsService(SousEtablissementsRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public SousEtablissements create(SousEtablissementsDTO dto) {
        SousEtablissements s = new SousEtablissements();
        s.setTitrearabe(dto.getTitrearabe());
        s.setTitrefrancais(dto.getTitrefrancais());
        s.setDescarabe(dto.getDescarabe());
        s.setDescfrancais(dto.getDescfrancais());
        s.setLien(dto.getLien());
        s.setLogo(dto.getLogo());
        return repository.save(s);
    }

    @Transactional
    public SousEtablissements update(Long id, SousEtablissementsDTO dto) {
        SousEtablissements s = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Sous établissement non trouvé"));

        s.setTitrearabe(dto.getTitrearabe());
        s.setTitrefrancais(dto.getTitrefrancais());
        s.setDescarabe(dto.getDescarabe());
        s.setDescfrancais(dto.getDescfrancais());
        s.setLien(dto.getLien());
        s.setLogo(dto.getLogo());

        return repository.save(s);
    }

    @Transactional
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public SousEtablissements getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Sous établissement non trouvé"));
    }

    @Transactional(readOnly = true)
    public List<SousEtablissements> getAll() {
        return repository.findAll();
    }
}
