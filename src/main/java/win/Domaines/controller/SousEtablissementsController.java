package win.Domaines.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import win.Domaines.dto.SousEtablissementsDTO;
import win.Domaines.entity.SousEtablissements;
import win.Domaines.service.SousEtablissementsService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/sousetablissements")
public class SousEtablissementsController {

    private final SousEtablissementsService service;

    public SousEtablissementsController(SousEtablissementsService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<SousEtablissementsDTO> create(@RequestBody SousEtablissementsDTO dto) {
        SousEtablissements saved = service.create(dto);
        return ResponseEntity.ok(SousEtablissementsDTO.fromEntity(saved));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SousEtablissementsDTO> update(@PathVariable Long id, @RequestBody SousEtablissementsDTO dto) {
        SousEtablissements updated = service.update(id, dto);
        return ResponseEntity.ok(SousEtablissementsDTO.fromEntity(updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        service.deleteById(id);
        return ResponseEntity.ok("Sous établissement supprimé avec succès");
    }

    @GetMapping("/{id}")
    public ResponseEntity<SousEtablissementsDTO> getById(@PathVariable Long id) {
        SousEtablissements s = service.getById(id);
        return ResponseEntity.ok(SousEtablissementsDTO.fromEntity(s));
    }

    @GetMapping
    public ResponseEntity<List<SousEtablissementsDTO>> getAll() {
        List<SousEtablissementsDTO> list = service.getAll()
                .stream()
                .map(SousEtablissementsDTO::fromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(list);
    }

}
