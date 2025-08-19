package win.Domaines.service;

import org.springframework.stereotype.Service;
import win.Domaines.dto.ActualitesCategoriesDTO;
import win.Domaines.entity.ActualitesCategories;
import win.Domaines.exception.ResourceNotFoundException;
import win.Domaines.repository.ActualitesCategoriesRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ActualitesCategoriesService {

    private final ActualitesCategoriesRepository repository;

    public ActualitesCategoriesService(ActualitesCategoriesRepository repository) {
        this.repository = repository;
    }

    public List<ActualitesCategoriesDTO> getAll() {
        return repository.findAll()
                .stream()
                .map(ActualitesCategoriesDTO::fromEntity)
                .collect(Collectors.toList());
    }

    public ActualitesCategoriesDTO getById(Long id) {
        ActualitesCategories entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("ActualitesCategories not found with id " + id));
        return ActualitesCategoriesDTO.fromEntity(entity);
    }

    public ActualitesCategoriesDTO create(ActualitesCategoriesDTO dto) {
        ActualitesCategories entity = new ActualitesCategories();
        entity.setNomarabe(dto.getNomarabe());
        entity.setNomfrancais(dto.getNomfrancais());
        return ActualitesCategoriesDTO.fromEntity(repository.save(entity));
    }

    public ActualitesCategoriesDTO update(Long id, ActualitesCategoriesDTO dto) {
        ActualitesCategories entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("ActualitesCategories not found with id " + id));
        entity.setNomarabe(dto.getNomarabe());
        entity.setNomfrancais(dto.getNomfrancais());
        return ActualitesCategoriesDTO.fromEntity(repository.save(entity));
    }

    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("ActualitesCategories not found with id " + id);
        }
        repository.deleteById(id);
    }
}
