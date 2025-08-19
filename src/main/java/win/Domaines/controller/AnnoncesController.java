package win.Domaines.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import win.Domaines.dto.AnnoncesDTO;
import win.Domaines.entity.Annonces;
import win.Domaines.service.AnnoncesService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/annonces")
public class AnnoncesController {

    private final AnnoncesService service;

    public AnnoncesController(AnnoncesService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<AnnoncesDTO> create(@RequestBody AnnoncesDTO dto) {
        Annonces saved = service.create(dto);
        return ResponseEntity.ok(AnnoncesDTO.fromEntity(saved));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AnnoncesDTO> update(@PathVariable Long id, @RequestBody AnnoncesDTO dto) {
        Annonces updated = service.update(id, dto);
        return ResponseEntity.ok(AnnoncesDTO.fromEntity(updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        service.deleteById(id);
        return ResponseEntity.ok("Annonce supprimée avec succès");
    }

    @GetMapping("/{id}")
    public ResponseEntity<AnnoncesDTO> getById(@PathVariable Long id) {
        Annonces a = service.getById(id);
        return ResponseEntity.ok(AnnoncesDTO.fromEntity(a));
    }

    @GetMapping
    public ResponseEntity<List<AnnoncesDTO>> getAll() {
        List<AnnoncesDTO> list = service.getAll()
                .stream()
                .map(AnnoncesDTO::fromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(list);
    }
}
