package win.Domaines.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import win.Domaines.dto.StructureAdministrativeDTO;
import win.Domaines.entity.StructureAdministrative;
import win.Domaines.service.ErrorLogService;
import win.Domaines.service.StructureAdministrativeService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/structureadministrative")
public class StructureAdministrativeController {

    private final StructureAdministrativeService service;
    private final ErrorLogService errorLogService;

    public StructureAdministrativeController(StructureAdministrativeService service, ErrorLogService errorLogService) {
        this.service = service;
        this.errorLogService = errorLogService;
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody StructureAdministrativeDTO dto) {
        try {
            StructureAdministrative saved = service.create(dto);
            return ResponseEntity.ok(StructureAdministrativeDTO.fromEntity(saved));
        } catch (Exception ex) {
            String ticket = errorLogService.logError("Create StructureAdministrative failed: " + ex.getMessage(), ex);
            return ResponseEntity.badRequest().body("Error creating StructureAdministrative. Ticket: " + ticket);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody StructureAdministrativeDTO dto) {
        try {
            StructureAdministrative updated = service.update(id, dto);
            return ResponseEntity.ok(StructureAdministrativeDTO.fromEntity(updated));
        } catch (Exception ex) {
            String ticket = errorLogService.logError("Update StructureAdministrative failed: " + ex.getMessage(), ex);
            return ResponseEntity.badRequest().body("Error updating StructureAdministrative. Ticket: " + ticket);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        try {
            service.deleteById(id);
            return ResponseEntity.ok("StructureAdministrative deleted successfully");
        } catch (Exception ex) {
            String ticket = errorLogService.logError("Delete StructureAdministrative failed: " + ex.getMessage(), ex);
            return ResponseEntity.badRequest().body("Error deleting StructureAdministrative. Ticket: " + ticket);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        try {
            StructureAdministrative s = service.getById(id);
            return ResponseEntity.ok(StructureAdministrativeDTO.fromEntity(s));
        } catch (Exception ex) {
            String ticket = errorLogService.logError("Get StructureAdministrative by id failed: " + ex.getMessage(), ex);
            return ResponseEntity.badRequest().body("Error fetching StructureAdministrative. Ticket: " + ticket);
        }
    }

    @GetMapping
    public ResponseEntity<List<StructureAdministrativeDTO>> getAll() {
        List<StructureAdministrativeDTO> list = service.getAll()
                .stream()
                .map(StructureAdministrativeDTO::fromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(list);
    }
}
