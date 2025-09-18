package win.Domaines.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import win.Domaines.dto.BibliothequeDTO;
import win.Domaines.entity.Bibliotheque;
import win.Domaines.entity.BibliothequeCategorie;
import win.Domaines.entity.BibliothequeFichier;
import win.Domaines.exception.ResourceNotFoundException;
import win.Domaines.repository.BibliothequeRepository;
import win.Domaines.repository.BibliothequeCategorieRepository;

import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BibliothequeService {

    private final BibliothequeRepository repository;
    private final BibliothequeCategorieRepository categorieRepository;

    public BibliothequeService(BibliothequeRepository repository,
                               BibliothequeCategorieRepository categorieRepository) {
        this.repository = repository;
        this.categorieRepository = categorieRepository;
    }

    @Transactional
    public Bibliotheque create(BibliothequeDTO dto) {
        Bibliotheque b = new Bibliotheque();
        b.setDescriptionarabe(dto.getDescriptionarabe());
        b.setDescriptionfrancais(dto.getDescriptionfrancais());
        b.setOrdre(dto.getOrdre());
        b.setEtat(dto.getEtat());

        if (dto.getCategorieId() != null) {
            BibliothequeCategorie cat = categorieRepository.findById(dto.getCategorieId())
                    .orElseThrow(() -> new ResourceNotFoundException("Categorie not found"));
            b.setCategorie(cat);
        }

        if (dto.getFichiers() != null) {
            List<BibliothequeFichier> fichiers = dto.getFichiers().stream()
                    .map(f -> new BibliothequeFichier(Base64.getDecoder().decode(f), b))
                    .collect(Collectors.toList());
            b.setFichiers(fichiers);
        }

        return repository.save(b);
    }

    @Transactional
    public Bibliotheque update(Long id, BibliothequeDTO dto) {
        Bibliotheque b = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Bibliotheque not found"));

        b.setDescriptionarabe(dto.getDescriptionarabe());
        b.setDescriptionfrancais(dto.getDescriptionfrancais());
        b.setOrdre(dto.getOrdre());
        b.setEtat(dto.getEtat());

        if (dto.getCategorieId() != null) {
            BibliothequeCategorie cat = categorieRepository.findById(dto.getCategorieId())
                    .orElseThrow(() -> new ResourceNotFoundException("Categorie not found"));
            b.setCategorie(cat);
        }

        if (dto.getFichiers() != null) {
            b.getFichiers().clear();
            List<BibliothequeFichier> fichiers = dto.getFichiers().stream()
                    .map(f -> new BibliothequeFichier(Base64.getDecoder().decode(f), b))
                    .collect(Collectors.toList());
            b.setFichiers(fichiers);
        }

        return repository.save(b);
    }

    @Transactional
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public Bibliotheque getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Bibliotheque not found"));
    }

    @Transactional(readOnly = true)
    public List<Bibliotheque> getAll() {
        return repository.findAll();
    }

    @Transactional(readOnly = true)
    public List<Bibliotheque> findByBibliothequeCategorie(Long categorieId) {
        BibliothequeCategorie cat = categorieRepository.findById(categorieId)
                .orElseThrow(() -> new ResourceNotFoundException("Categorie not found"));
        return repository.findByCategorie(cat);
    }
}
