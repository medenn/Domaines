package win.Domaines.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import win.Domaines.dto.AnnoncesDTO;
import win.Domaines.entity.Annonces;
import win.Domaines.repository.AnnoncesRepository;

import java.util.List;

@Service
public class AnnoncesService {

    private final AnnoncesRepository repository;

    public AnnoncesService(AnnoncesRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public Annonces create(AnnoncesDTO dto) {
        Annonces a = new Annonces();
        a.setTitrearabe(dto.getTitrearabe());
        a.setTitrefrancais(dto.getTitrefrancais());
        a.setTextarabe(dto.getTextarabe());
        a.setTextfrancais(dto.getTextfrancais());
        a.setDatepublication(dto.getDatepublication());
        return repository.save(a);
    }

    @Transactional
    public Annonces update(Long id, AnnoncesDTO dto) {
        Annonces a = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Annonce non trouvée"));

        a.setTitrearabe(dto.getTitrearabe());
        a.setTitrefrancais(dto.getTitrefrancais());
        a.setTextarabe(dto.getTextarabe());
        a.setTextfrancais(dto.getTextfrancais());
        a.setDatepublication(dto.getDatepublication());
        return repository.save(a);
    }

    @Transactional
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public Annonces getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Annonce non trouvée"));
    }

    @Transactional(readOnly = true)
    public List<Annonces> getAll() {
        return repository.findAll();
    }
}
