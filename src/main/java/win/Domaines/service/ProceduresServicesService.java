package win.Domaines.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import win.Domaines.dto.ProceduresServicesDTO;
import win.Domaines.entity.ProceduresServices;
import win.Domaines.repository.ProceduresServicesRepository;

import java.util.Base64;
import java.util.List;

@Service
public class ProceduresServicesService {

    private final ProceduresServicesRepository repository;

    public ProceduresServicesService(ProceduresServicesRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public ProceduresServices create(ProceduresServicesDTO dto) {
        ProceduresServices p = new ProceduresServices();
        p.setTitrearabe(dto.getTitrearabe());
        p.setTitrefrancais(dto.getTitrefrancais());
        p.setTextarabe(dto.getTextarabe());
        p.setTextfrancais(dto.getTextfrancais());

        if (dto.getImage() != null && !dto.getImage().isEmpty()) {
            p.setImage(Base64.getDecoder().decode(dto.getImage()));
        }

        if (dto.getFichier() != null && !dto.getFichier().isEmpty()) {
            p.setFichier(Base64.getDecoder().decode(dto.getFichier()));
        }

        return repository.save(p);
    }

    @Transactional
    public ProceduresServices update(Long id, ProceduresServicesDTO dto) {
        ProceduresServices p = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Procédure/Service non trouvé"));

        p.setTitrearabe(dto.getTitrearabe());
        p.setTitrefrancais(dto.getTitrefrancais());
        p.setTextarabe(dto.getTextarabe());
        p.setTextfrancais(dto.getTextfrancais());

        if (dto.getImage() != null && !dto.getImage().isEmpty()) {
            p.setImage(Base64.getDecoder().decode(dto.getImage()));
        }

        if (dto.getFichier() != null && !dto.getFichier().isEmpty()) {
            p.setFichier(Base64.getDecoder().decode(dto.getFichier()));
        }

        return repository.save(p);
    }

    @Transactional
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public ProceduresServices getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Procédure/Service non trouvé"));
    }

    @Transactional(readOnly = true)
    public List<ProceduresServices> getAll() {
        return repository.findAll();
    }

 
    @Transactional(readOnly = true)
public String getImageBase64(Long id) {
    ProceduresServices p = getById(id);
    return p.getImage() != null ? Base64.getEncoder().encodeToString(p.getImage()) : null;
}

@Transactional(readOnly = true)
public String getFichierBase64(Long id) {
    ProceduresServices p = getById(id);
    return p.getFichier() != null ? Base64.getEncoder().encodeToString(p.getFichier()) : null;
}

}
