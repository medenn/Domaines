package win.Domaines.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import win.Domaines.dto.BibliothequeCategorieDTO;
import win.Domaines.entity.BibliothequeCategorie;
import win.Domaines.exception.ResourceNotFoundException;
import win.Domaines.repository.BibliothequeCategorieRepository;

import java.util.List;

@Service
public class BibliothequeCategorieService {

    private final BibliothequeCategorieRepository repository;

    public BibliothequeCategorieService(BibliothequeCategorieRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public BibliothequeCategorie create(BibliothequeCategorieDTO dto) {
        BibliothequeCategorie c = new BibliothequeCategorie();
        c.setNomarabe(dto.getNomarabe());
        c.setNomfrancais(dto.getNomfrancais());
        return repository.save(c);
    }

    @Transactional
    public BibliothequeCategorie update(Long id, BibliothequeCategorieDTO dto) {
        BibliothequeCategorie c = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("BibliothequeCategorie not found"));
        c.setNomarabe(dto.getNomarabe());
        c.setNomfrancais(dto.getNomfrancais());
        return repository.save(c);
    }

    @Transactional
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public BibliothequeCategorie getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("BibliothequeCategorie not found"));
    }

    @Transactional(readOnly = true)
    public List<BibliothequeCategorie> getAll() {
        return repository.findAll();
    }
}
