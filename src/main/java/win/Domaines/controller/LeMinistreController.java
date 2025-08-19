package win.Domaines.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import win.Domaines.dto.LeMinistreDTO;
import win.Domaines.entity.LeMinistre;
import win.Domaines.service.ErrorLogService;
import win.Domaines.service.LeMinistreService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/leministre")
public class LeMinistreController {

    private final LeMinistreService service;
    private final ErrorLogService errorLogService;

    public LeMinistreController(LeMinistreService service, ErrorLogService errorLogService) {
        this.service = service;
        this.errorLogService = errorLogService;
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody LeMinistreDTO dto) {
        try {
            LeMinistre saved = service.create(dto);
            return ResponseEntity.ok(LeMinistreDTO.fromEntity(saved));
        } catch (Exception ex) {
            String ticket = errorLogService.logError("Create LeMinistre failed: " + ex.getMessage(), ex);
            return ResponseEntity.badRequest().body("Error creating LeMinistre. Ticket: " + ticket);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody LeMinistreDTO dto) {
        try {
            LeMinistre updated = service.update(id, dto);
            return ResponseEntity.ok(LeMinistreDTO.fromEntity(updated));
        } catch (Exception ex) {
            String ticket = errorLogService.logError("Update LeMinistre failed: " + ex.getMessage(), ex);
            return ResponseEntity.badRequest().body("Error updating LeMinistre. Ticket: " + ticket);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        try {
            service.deleteById(id);
            return ResponseEntity.ok("LeMinistre deleted successfully");
        } catch (Exception ex) {
            String ticket = errorLogService.logError("Delete LeMinistre failed: " + ex.getMessage(), ex);
            return ResponseEntity.badRequest().body("Error deleting LeMinistre. Ticket: " + ticket);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        try {
            LeMinistre ministre = service.getById(id);
            return ResponseEntity.ok(LeMinistreDTO.fromEntity(ministre));
        } catch (Exception ex) {
            String ticket = errorLogService.logError("Get LeMinistre by id failed: " + ex.getMessage(), ex);
            return ResponseEntity.badRequest().body("Error fetching LeMinistre. Ticket: " + ticket);
        }
    }

    @GetMapping
    public ResponseEntity<List<LeMinistreDTO>> getAll() {
        List<LeMinistreDTO> list = service.getAll()
                .stream()
                .map(LeMinistreDTO::fromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(list);
    }
}
