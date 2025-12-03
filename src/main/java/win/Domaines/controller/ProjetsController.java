package win.Domaines.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import win.Domaines.dto.ProjetsDTO;
import win.Domaines.entity.Projets;
import win.Domaines.service.ProjetsService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/projets")
public class ProjetsController {

    private final ProjetsService service;

    public ProjetsController(ProjetsService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<ProjetsDTO> create(@RequestBody ProjetsDTO dto) {
        Projets saved = service.create(dto);
        return ResponseEntity.ok(ProjetsDTO.fromEntity(saved));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProjetsDTO> update(@PathVariable Long id,
                                             @RequestBody ProjetsDTO dto) {
        Projets updated = service.update(id, dto);
        return ResponseEntity.ok(ProjetsDTO.fromEntity(updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.ok("Projet supprimé avec succès");
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProjetsDTO> getById(@PathVariable Long id) {
        Projets p = service.getById(id);
        return ResponseEntity.ok(ProjetsDTO.fromEntity(p));
    }

    @GetMapping
    public ResponseEntity<List<ProjetsDTO>> getAll() {
        List<ProjetsDTO> list = service.getAll()
                .stream()
                .map(ProjetsDTO::fromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(list);
    }
}
