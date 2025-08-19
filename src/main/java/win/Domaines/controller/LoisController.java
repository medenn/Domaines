package win.Domaines.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import win.Domaines.dto.LoisDTO;
import win.Domaines.entity.Lois;
import win.Domaines.service.LoisService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/lois")
public class LoisController {

    private final LoisService service;

    public LoisController(LoisService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<LoisDTO> create(@RequestBody LoisDTO dto) {
        Lois saved = service.create(dto);
        return ResponseEntity.ok(LoisDTO.fromEntity(saved));
    }

    @PutMapping("/{id}")
    public ResponseEntity<LoisDTO> update(@PathVariable Long id, @RequestBody LoisDTO dto) {
        Lois updated = service.update(id, dto);
        return ResponseEntity.ok(LoisDTO.fromEntity(updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        service.deleteById(id);
        return ResponseEntity.ok("Lois supprimée avec succès");
    }

    @GetMapping("/{id}")
    public ResponseEntity<LoisDTO> getById(@PathVariable Long id) {
        Lois l = service.getById(id);
        return ResponseEntity.ok(LoisDTO.fromEntity(l));
    }

    @GetMapping
    public ResponseEntity<List<LoisDTO>> getAll() {
        List<LoisDTO> list = service.getAll()
                .stream()
                .map(LoisDTO::fromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(list);
    }
}
