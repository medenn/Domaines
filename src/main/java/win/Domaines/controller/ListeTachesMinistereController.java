package win.Domaines.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import win.Domaines.dto.ListeTachesMinistereDTO;
import win.Domaines.entity.ListeTachesMinistere;
import win.Domaines.service.ErrorLogService;
import win.Domaines.service.ListeTachesMinistereService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/listetaches")
public class ListeTachesMinistereController {

    private final ListeTachesMinistereService service;
    private final ErrorLogService errorLogService;

    public ListeTachesMinistereController(ListeTachesMinistereService service, ErrorLogService errorLogService) {
        this.service = service;
        this.errorLogService = errorLogService;
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody ListeTachesMinistereDTO dto) {
        try {
            ListeTachesMinistere saved = service.create(dto);
            return ResponseEntity.ok(ListeTachesMinistereDTO.fromEntity(saved));
        } catch (Exception ex) {
            String ticket = errorLogService.logError("Create listeTaches failed: " + ex.getMessage(), ex);
            return ResponseEntity.badRequest().body("Error creating listeTaches. Ticket: " + ticket);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody ListeTachesMinistereDTO dto) {
        try {
            ListeTachesMinistere updated = service.update(id, dto);
            return ResponseEntity.ok(ListeTachesMinistereDTO.fromEntity(updated));
        } catch (Exception ex) {
            String ticket = errorLogService.logError("Update listeTaches failed: " + ex.getMessage(), ex);
            return ResponseEntity.badRequest().body("Error updating listeTaches. Ticket: " + ticket);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        try {
            service.deleteById(id);
            return ResponseEntity.ok("ListeTachesMinistere deleted successfully");
        } catch (Exception ex) {
            String ticket = errorLogService.logError("Delete listeTaches failed: " + ex.getMessage(), ex);
            return ResponseEntity.badRequest().body("Error deleting listeTaches. Ticket: " + ticket);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        try {
            ListeTachesMinistere l = service.getById(id);
            return ResponseEntity.ok(ListeTachesMinistereDTO.fromEntity(l));
        } catch (Exception ex) {
            String ticket = errorLogService.logError("Get listeTaches by id failed: " + ex.getMessage(), ex);
            return ResponseEntity.badRequest().body("Error fetching listeTaches. Ticket: " + ticket);
        }
    }

    @GetMapping
    public ResponseEntity<List<ListeTachesMinistereDTO>> getAll() {
        List<ListeTachesMinistereDTO> list = service.getAll()
                .stream()
                .map(ListeTachesMinistereDTO::fromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(list);
    }


     @GetMapping("/bytache/{tacheid}")
    public ResponseEntity<?> getByTacheId(@PathVariable Long tacheid) {
        try {
            List<ListeTachesMinistereDTO> list = service.findByTacheId(tacheid);
            return ResponseEntity.ok(list);
        } catch (Exception ex) {
            String ticket = errorLogService.logError("Get ListeTachesMinistere by tacheid failed: " + ex.getMessage(), ex);
            return ResponseEntity.badRequest().body("Error fetching ListeTachesMinistere by tacheid. Ticket: " + ticket);
        }
    }
}
