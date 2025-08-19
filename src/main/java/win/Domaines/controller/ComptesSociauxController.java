package win.Domaines.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import win.Domaines.dto.ComptesSociauxDTO;
import win.Domaines.entity.ComptesSociaux;
import win.Domaines.service.ComptesSociauxService;
import win.Domaines.service.ErrorLogService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/comptessociaux")
public class ComptesSociauxController {

    private final ComptesSociauxService comptesSociauxService;
    private final ErrorLogService errorLogService;

    public ComptesSociauxController(ComptesSociauxService comptesSociauxService, ErrorLogService errorLogService) {
        this.comptesSociauxService = comptesSociauxService;
        this.errorLogService = errorLogService;
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody ComptesSociauxDTO dto) {
        try {
            ComptesSociaux saved = comptesSociauxService.create(dto);
            return ResponseEntity.ok(ComptesSociauxDTO.fromEntity(saved));
        } catch (Exception ex) {
            String ticket = errorLogService.logError("Create compte social failed: " + ex.getMessage(), ex);
            return ResponseEntity.badRequest().body("Error creating compte social. Ticket: " + ticket);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody ComptesSociauxDTO dto) {
        try {
            ComptesSociaux updated = comptesSociauxService.update(id, dto);
            return ResponseEntity.ok(ComptesSociauxDTO.fromEntity(updated));
        } catch (Exception ex) {
            String ticket = errorLogService.logError("Update compte social failed: " + ex.getMessage(), ex);
            return ResponseEntity.badRequest().body("Error updating compte social. Ticket: " + ticket);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        try {
            comptesSociauxService.deleteById(id);
            return ResponseEntity.ok("Compte social deleted successfully");
        } catch (Exception ex) {
            String ticket = errorLogService.logError("Delete compte social failed: " + ex.getMessage(), ex);
            return ResponseEntity.badRequest().body("Error deleting compte social. Ticket: " + ticket);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        try {
            ComptesSociaux compte = comptesSociauxService.getById(id);
            return ResponseEntity.ok(ComptesSociauxDTO.fromEntity(compte));
        } catch (Exception ex) {
            String ticket = errorLogService.logError("Get compte social by id failed: " + ex.getMessage(), ex);
            return ResponseEntity.badRequest().body("Error fetching compte social. Ticket: " + ticket);
        }
    }

    @GetMapping
    public ResponseEntity<List<ComptesSociauxDTO>> getAll() {
        List<ComptesSociauxDTO> list = comptesSociauxService.getAll()
                .stream()
                .map(ComptesSociauxDTO::fromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(list);
    }
}
