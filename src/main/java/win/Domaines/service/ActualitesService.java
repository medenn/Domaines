package win.Domaines.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import win.Domaines.dto.ActualitesDTO;
import win.Domaines.entity.Actualites;
import win.Domaines.entity.ActualitesCategories;
import win.Domaines.exception.ResourceNotFoundException;
import win.Domaines.repository.ActualitesRepository;
import win.Domaines.repository.ActualitesCategoriesRepository;

import java.util.Base64;
import java.util.List;

@Service
public class ActualitesService {

    private final ActualitesRepository repository;
    private final ActualitesCategoriesRepository categorieRepository;

    public ActualitesService(ActualitesRepository repository, ActualitesCategoriesRepository categorieRepository) {
        this.repository = repository;
        this.categorieRepository = categorieRepository;
    }

    @Transactional
    public Actualites create(ActualitesDTO dto) {
        Actualites a = new Actualites();
        a.setTitrearabe(dto.getTitrearabe());
        a.setTitrefrancais(dto.getTitrefrancais());
        a.setTextarabe(dto.getTextarabe());
        a.setTextfrancais(dto.getTextfrancais());
        a.setDatepublication(dto.getDatepublication());
        a.setLienar(dto.getLienar());
        a.setLienfr(dto.getLienfr());

        if (dto.getImage() != null && !dto.getImage().isEmpty()) {
            a.setImage(Base64.getDecoder().decode(dto.getImage()));
        }

        // Associer la catégorie
        if (dto.getCategorieid() != null) {
            ActualitesCategories categorie = categorieRepository.findById(dto.getCategorieid())
                    .orElseThrow(() -> new ResourceNotFoundException("Catégorie non trouvée avec l'id " + dto.getCategorieid()));
            a.setCategorie(categorie);
        } else {
            throw new IllegalArgumentException("La catégorie (categorieid) est obligatoire");
        }

        return repository.save(a);
    }

    @Transactional
    public Actualites update(Long id, ActualitesDTO dto) {
        Actualites a = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Actualité non trouvée"));

        a.setTitrearabe(dto.getTitrearabe());
        a.setTitrefrancais(dto.getTitrefrancais());
        a.setTextarabe(dto.getTextarabe());
        a.setTextfrancais(dto.getTextfrancais());
        a.setDatepublication(dto.getDatepublication());
        a.setLienar(dto.getLienar());
        a.setLienfr(dto.getLienfr());

        if (dto.getImage() != null && !dto.getImage().isEmpty()) {
            a.setImage(Base64.getDecoder().decode(dto.getImage()));
        }

        // Mettre à jour la catégorie si fournie
        if (dto.getCategorieid() != null) {
            ActualitesCategories categorie = categorieRepository.findById(dto.getCategorieid())
                    .orElseThrow(() -> new ResourceNotFoundException("Catégorie non trouvée avec l'id " + dto.getCategorieid()));
            a.setCategorie(categorie);
        }

        return repository.save(a);
    }

    @Transactional
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public Actualites getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Actualité non trouvée"));
    }

    @Transactional(readOnly = true)
    public List<Actualites> getAll() {
        return repository.findAll();
    }

    @Transactional(readOnly = true)
    public List<Actualites> getByActualitesCategories(Long categorieid) {
        return repository.findByCategorie_Id(categorieid);
    }
}
