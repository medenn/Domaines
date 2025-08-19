package win.Domaines.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import win.Domaines.dto.ActualitesDTO;
import win.Domaines.entity.Actualites;
import win.Domaines.service.ActualitesService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/actualites")
public class ActualitesController {

    private final ActualitesService service;

    public ActualitesController(ActualitesService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<ActualitesDTO> create(@RequestBody ActualitesDTO dto) {
        Actualites saved = service.create(dto);
        return ResponseEntity.ok(ActualitesDTO.fromEntity(saved));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ActualitesDTO> update(@PathVariable Long id, @RequestBody ActualitesDTO dto) {
        Actualites updated = service.update(id, dto);
        return ResponseEntity.ok(ActualitesDTO.fromEntity(updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        service.deleteById(id);
        return ResponseEntity.ok("Actualité supprimée avec succès");
    }

    @GetMapping("/{id}")
    public ResponseEntity<ActualitesDTO> getById(@PathVariable Long id) {
        Actualites a = service.getById(id);
        return ResponseEntity.ok(ActualitesDTO.fromEntity(a));
    }

    @GetMapping
    public ResponseEntity<List<ActualitesDTO>> getAll() {
        List<ActualitesDTO> list = service.getAll()
                .stream()
                .map(ActualitesDTO::fromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(list);
    }

    @GetMapping("/bycategorie/{categorieid}")
    public ResponseEntity<List<ActualitesDTO>> getByCategorie(@PathVariable Long categorieid) {
        List<ActualitesDTO> list = service.getByActualitesCategories(categorieid)
                .stream()
                .map(ActualitesDTO::fromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(list);
    }
}
