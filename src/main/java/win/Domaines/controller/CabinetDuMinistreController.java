package win.Domaines.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import win.Domaines.dto.CabinetDuMinistreDTO;
import win.Domaines.entity.CabinetDuMinistre;
import win.Domaines.service.CabinetDuMinistreService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/cabinetministre")
public class CabinetDuMinistreController {

    private final CabinetDuMinistreService service;

    public CabinetDuMinistreController(CabinetDuMinistreService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<CabinetDuMinistreDTO> create(@RequestBody CabinetDuMinistreDTO dto) {
        CabinetDuMinistre saved = service.create(dto);
        return ResponseEntity.ok(CabinetDuMinistreDTO.fromEntity(saved));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CabinetDuMinistreDTO> update(@PathVariable Long id,
                                                       @RequestBody CabinetDuMinistreDTO dto) {
        CabinetDuMinistre updated = service.update(id, dto);
        return ResponseEntity.ok(CabinetDuMinistreDTO.fromEntity(updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.ok("Supprimé avec succès");
    }

    @GetMapping("/{id}")
    public ResponseEntity<CabinetDuMinistreDTO> getById(@PathVariable Long id) {
        CabinetDuMinistre data = service.getById(id);
        return ResponseEntity.ok(CabinetDuMinistreDTO.fromEntity(data));
    }

    @GetMapping
    public ResponseEntity<List<CabinetDuMinistreDTO>> getAll() {
        List<CabinetDuMinistreDTO> list = service.getAll()
                .stream()
                .map(CabinetDuMinistreDTO::fromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(list);
    }
}
