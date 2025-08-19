package win.Domaines.service;

import org.springframework.stereotype.Service;
import win.Domaines.dto.ListeTachesMinistereDTO;
import win.Domaines.entity.ListeTachesMinistere;
import win.Domaines.entity.TachesMinistere;
import win.Domaines.exception.ResourceNotFoundException;
import win.Domaines.repository.ListeTachesMinistereRepository;
import win.Domaines.repository.TachesMinistereRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ListeTachesMinistereService {

    private final ListeTachesMinistereRepository listeTachesRepository;
    private final TachesMinistereRepository tachesRepository;

    public ListeTachesMinistereService(ListeTachesMinistereRepository listeTachesRepository,
            TachesMinistereRepository tachesRepository) {
        this.listeTachesRepository = listeTachesRepository;
        this.tachesRepository = tachesRepository;
    }

    public ListeTachesMinistere create(ListeTachesMinistereDTO dto) {
        TachesMinistere tache = tachesRepository.findById(dto.getTacheid())
                .orElseThrow(
                        () -> new ResourceNotFoundException("TachesMinistere not found with id " + dto.getTacheid()));

        ListeTachesMinistere l = new ListeTachesMinistere();
        l.setTachesMinistere(tache);
        l.setTitrearabe(dto.getTitrearabe());
        l.setTitrefrancais(dto.getTitrefrancais());

        return listeTachesRepository.save(l);
    }

    public ListeTachesMinistere update(Long id, ListeTachesMinistereDTO dto) {
        ListeTachesMinistere l = listeTachesRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("ListeTachesMinistere not found with id " + id));

        if (dto.getTacheid() != null) {
            TachesMinistere tache = tachesRepository.findById(dto.getTacheid())
                    .orElseThrow(() -> new ResourceNotFoundException(
                            "TachesMinistere not found with id " + dto.getTacheid()));
            l.setTachesMinistere(tache);
        }

        l.setTitrearabe(dto.getTitrearabe());
        l.setTitrefrancais(dto.getTitrefrancais());

        return listeTachesRepository.save(l);
    }

    public void deleteById(Long id) {
        listeTachesRepository.deleteById(id);
    }

    public ListeTachesMinistere getById(Long id) {
        return listeTachesRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("ListeTachesMinistere not found with id " + id));
    }


      public List<ListeTachesMinistereDTO> findByTacheId(Long tacheid) {
        return listeTachesRepository.findByTachesMinistere_Id(tacheid)
                .stream()
                .map(ListeTachesMinistereDTO::fromEntity)
                .collect(Collectors.toList());
    }

   
    public List<ListeTachesMinistere> getAll() {
        return listeTachesRepository.findAll();
    }
}
