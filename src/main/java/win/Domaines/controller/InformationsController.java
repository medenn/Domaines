package win.Domaines.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import win.Domaines.dto.InformationsDTO;
import win.Domaines.entity.Informations;
import win.Domaines.service.ErrorLogService;
import win.Domaines.service.InformationsService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/informations")
public class InformationsController {

    private final InformationsService informationsService;
    private final ErrorLogService errorLogService;

    public InformationsController(InformationsService informationsService, ErrorLogService errorLogService) {
        this.informationsService = informationsService;
        this.errorLogService = errorLogService;
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody InformationsDTO dto) {
        try {
            Informations saved = informationsService.create(dto);
            return ResponseEntity.ok(InformationsDTO.fromEntity(saved));
        } catch (Exception ex) {
            String ticket = errorLogService.logError("Create informations failed: " + ex.getMessage(), ex);
            return ResponseEntity.badRequest().body("Error creating informations. Ticket: " + ticket);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody InformationsDTO dto) {
        try {
            Informations updated = informationsService.update(id, dto);
            return ResponseEntity.ok(InformationsDTO.fromEntity(updated));
        } catch (Exception ex) {
            String ticket = errorLogService.logError("Update informations failed: " + ex.getMessage(), ex);
            return ResponseEntity.badRequest().body("Error updating informations. Ticket: " + ticket);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        try {
            informationsService.deleteById(id);
            return ResponseEntity.ok("Informations deleted successfully");
        } catch (Exception ex) {
            String ticket = errorLogService.logError("Delete informations failed: " + ex.getMessage(), ex);
            return ResponseEntity.badRequest().body("Error deleting informations. Ticket: " + ticket);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        try {
            Informations informations = informationsService.getById(id);
            return ResponseEntity.ok(InformationsDTO.fromEntity(informations));
        } catch (Exception ex) {
            String ticket = errorLogService.logError("Get informations by id failed: " + ex.getMessage(), ex);
            return ResponseEntity.badRequest().body("Error fetching informations. Ticket: " + ticket);
        }
    }

    @GetMapping
    public ResponseEntity<List<InformationsDTO>> getAll() {
        List<InformationsDTO> list = informationsService.getAll()
                .stream()
                .map(InformationsDTO::fromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(list);
    }
}
