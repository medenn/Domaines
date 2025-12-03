package win.Domaines.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import win.Domaines.dto.MotMinistreDTO;
import win.Domaines.entity.MotMinistre;
import win.Domaines.service.ErrorLogService;
import win.Domaines.service.MotMinistreService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/motministre")
public class MotMinistreController {


private final MotMinistreService service;
private final ErrorLogService errorLogService;

public MotMinistreController(MotMinistreService service, ErrorLogService errorLogService) {
    this.service = service;
    this.errorLogService = errorLogService;
}

@PostMapping
public ResponseEntity<?> create(@RequestBody MotMinistreDTO dto) {
    try {
        MotMinistre saved = service.create(dto);
        return ResponseEntity.ok(MotMinistreDTO.fromEntity(saved));
    } catch (Exception ex) {
        String ticket = errorLogService.logError("Create MotMinistre failed: " + ex.getMessage(), ex);
        return ResponseEntity.badRequest().body("Error creating MotMinistre. Ticket: " + ticket);
    }
}

@PutMapping("/{id}")
public ResponseEntity<?> update(@PathVariable Long id, @RequestBody MotMinistreDTO dto) {
    try {
        MotMinistre updated = service.update(id, dto);
        return ResponseEntity.ok(MotMinistreDTO.fromEntity(updated));
    } catch (Exception ex) {
        String ticket = errorLogService.logError("Update MotMinistre failed: " + ex.getMessage(), ex);
        return ResponseEntity.badRequest().body("Error updating MotMinistre. Ticket: " + ticket);
    }
}

@DeleteMapping("/{id}")
public ResponseEntity<?> delete(@PathVariable Long id) {
    try {
        service.deleteById(id);
        return ResponseEntity.ok("MotMinistre deleted successfully");
    } catch (Exception ex) {
        String ticket = errorLogService.logError("Delete MotMinistre failed: " + ex.getMessage(), ex);
        return ResponseEntity.badRequest().body("Error deleting MotMinistre. Ticket: " + ticket);
    }
}

@GetMapping("/{id}")
public ResponseEntity<?> getById(@PathVariable Long id) {
    try {
        MotMinistre m = service.getById(id);
        return ResponseEntity.ok(MotMinistreDTO.fromEntity(m));
    } catch (Exception ex) {
        String ticket = errorLogService.logError("Get MotMinistre by id failed: " + ex.getMessage(), ex);
        return ResponseEntity.badRequest().body("Error fetching MotMinistre. Ticket: " + ticket);
    }
}

@GetMapping
public ResponseEntity<List<MotMinistreDTO>> getAll() {
    List<MotMinistreDTO> list = service.getAll()
            .stream()
            .map(MotMinistreDTO::fromEntity)
            .collect(Collectors.toList());
    return ResponseEntity.ok(list);
}


}
