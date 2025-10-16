package win.Domaines.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import win.Domaines.dto.MinistereDTO;
import win.Domaines.entity.Ministere;
import win.Domaines.service.ErrorLogService;
import win.Domaines.service.MinistereService;

import java.util.List;
import java.util.stream.Collectors;
@RestController
@RequestMapping("/api/aproposministere")
public class MinistereController {

    private final MinistereService ministreService;
    private final ErrorLogService errorLogService;

    public MinistereController(MinistereService ministreService, ErrorLogService errorLogService) {
        this.ministreService = ministreService;
        this.errorLogService = errorLogService;
    }

     @PostMapping
    public ResponseEntity<?> create(@RequestBody MinistereDTO dto) {
        try {
            Ministere saved = ministreService.create(dto);
            return ResponseEntity.ok(MinistereDTO.fromEntity(saved));
        } catch (Exception ex) {
            String ticket = errorLogService.logError("Create ministre failed: " + ex.getMessage(), ex);
            return ResponseEntity.badRequest().body("Error creating ministre. Ticket: " + ticket);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody MinistereDTO dto) {
        try {
            Ministere updated = ministreService.update(id, dto);
            return ResponseEntity.ok(MinistereDTO.fromEntity(updated));
        } catch (Exception ex) {
            String ticket = errorLogService.logError("Update ministre failed: " + ex.getMessage(), ex);
            return ResponseEntity.badRequest().body("Error updating ministre. Ticket: " + ticket);
        }
    }

   

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        try {
            ministreService.deleteById(id);
            return ResponseEntity.ok("Ministre deleted successfully");
        } catch (Exception ex) {
            String ticket = errorLogService.logError("Delete ministre failed: " + ex.getMessage(), ex);
            return ResponseEntity.badRequest().body("Error deleting ministre. Ticket: " + ticket);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        try {
            Ministere ministre = ministreService.getById(id);
            return ResponseEntity.ok(MinistereDTO.fromEntity(ministre));
        } catch (Exception ex) {
            String ticket = errorLogService.logError("Get ministre by id failed: " + ex.getMessage(), ex);
            return ResponseEntity.badRequest().body("Error fetching ministre. Ticket: " + ticket);
        }
    }

    @GetMapping
    public ResponseEntity<List<MinistereDTO>> getAll() {
        List<MinistereDTO> list = ministreService.getAll()
                .stream()
                .map(MinistereDTO::fromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(list);
    }
}
