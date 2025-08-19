package win.Domaines.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import win.Domaines.dto.TachesMinistereDTO;
import win.Domaines.entity.TachesMinistere;
import win.Domaines.service.ErrorLogService;
import win.Domaines.service.TachesMinistereService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/tachesministere")
public class TachesMinistereController {

    private final TachesMinistereService tachesMinistereService;
    private final ErrorLogService errorLogService;

    public TachesMinistereController(TachesMinistereService tachesMinistereService, ErrorLogService errorLogService) {
        this.tachesMinistereService = tachesMinistereService;
        this.errorLogService = errorLogService;
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody TachesMinistereDTO dto) {
        try {
            TachesMinistere saved = tachesMinistereService.create(dto);
            return ResponseEntity.ok(TachesMinistereDTO.fromEntity(saved));
        } catch (Exception ex) {
            String ticket = errorLogService.logError("Create tâche ministère failed: " + ex.getMessage(), ex);
            return ResponseEntity.badRequest().body("Error creating tâche ministère. Ticket: " + ticket);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody TachesMinistereDTO dto) {
        try {
            TachesMinistere updated = tachesMinistereService.update(id, dto);
            return ResponseEntity.ok(TachesMinistereDTO.fromEntity(updated));
        } catch (Exception ex) {
            String ticket = errorLogService.logError("Update tâche ministère failed: " + ex.getMessage(), ex);
            return ResponseEntity.badRequest().body("Error updating tâche ministère. Ticket: " + ticket);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        try {
            tachesMinistereService.deleteById(id);
            return ResponseEntity.ok("Tâche ministère deleted successfully");
        } catch (Exception ex) {
            String ticket = errorLogService.logError("Delete tâche ministère failed: " + ex.getMessage(), ex);
            return ResponseEntity.badRequest().body("Error deleting tâche ministère. Ticket: " + ticket);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        try {
            TachesMinistere tache = tachesMinistereService.getById(id);
            return ResponseEntity.ok(TachesMinistereDTO.fromEntity(tache));
        } catch (Exception ex) {
            String ticket = errorLogService.logError("Get tâche ministère by id failed: " + ex.getMessage(), ex);
            return ResponseEntity.badRequest().body("Error fetching tâche ministère. Ticket: " + ticket);
        }
    }

    @GetMapping
    public ResponseEntity<List<TachesMinistereDTO>> getAll() {
        List<TachesMinistereDTO> list = tachesMinistereService.getAll()
                .stream()
                .map(TachesMinistereDTO::fromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(list);
    }
}
