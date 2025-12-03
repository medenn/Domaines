package win.Domaines.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import win.Domaines.dto.VideosDTO;
import win.Domaines.entity.Videos;
import win.Domaines.repository.VideosRepository;

import java.util.List;

@Service
public class VideosService {

    private final VideosRepository repository;

    public VideosService(VideosRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public Videos create(VideosDTO dto) {
        Videos v = new Videos();
        v.setTitrearabe(dto.getTitrearabe());
        v.setTitrefrancais(dto.getTitrefrancais());
        v.setDescarabe(dto.getDescarabe());
        v.setDescfrancais(dto.getDescfrancais());
        v.setFichier(dto.getFichier());
        v.setLien(dto.getLien());
        return repository.save(v);
    }

    @Transactional
    public Videos update(Long id, VideosDTO dto) {
        Videos v = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Vidéo non trouvée"));

        v.setTitrearabe(dto.getTitrearabe());
        v.setTitrefrancais(dto.getTitrefrancais());
        v.setDescarabe(dto.getDescarabe());
        v.setDescfrancais(dto.getDescfrancais());
        v.setFichier(dto.getFichier());
        v.setLien(dto.getLien());

        return repository.save(v);
    }

    @Transactional
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public Videos getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Vidéo non trouvée"));
    }

    @Transactional(readOnly = true)
    public List<Videos> getAll() {
        return repository.findAll();
    }
}
