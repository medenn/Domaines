package win.Domaines.service;

import org.springframework.stereotype.Service;
import win.Domaines.dto.MinistereContenuAssocierDTO;
import win.Domaines.entity.MinistereContenuAssocier;
import win.Domaines.exception.ResourceNotFoundException;
import win.Domaines.repository.MinistereContenuAssocierRepository;

import java.util.List;

@Service
public class MinistereContenuAssocierService {

    private final MinistereContenuAssocierRepository repository;

    public MinistereContenuAssocierService(MinistereContenuAssocierRepository repository) {
        this.repository = repository;
    }

    public MinistereContenuAssocier create(MinistereContenuAssocierDTO dto) {
        MinistereContenuAssocier m = new MinistereContenuAssocier(
                dto.getTitrearabe(),
                dto.getTitrefrancais(),
                dto.getTextarabe(),
                dto.getTextfrancais()
        );
        return repository.save(m);
    }

    public MinistereContenuAssocier update(Long id, MinistereContenuAssocierDTO dto) {
        MinistereContenuAssocier m = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Contenu associé non trouvé avec id " + id));

        m.setTitrearabe(dto.getTitrearabe());
        m.setTitrefrancais(dto.getTitrefrancais());
        m.setTextarabe(dto.getTextarabe());
        m.setTextfrancais(dto.getTextfrancais());

        return repository.save(m);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    public MinistereContenuAssocier getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Contenu associé non trouvé avec id " + id));
    }

    public List<MinistereContenuAssocier> getAll() {
        return repository.findAll();
    }
}
