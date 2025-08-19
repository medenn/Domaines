package win.Domaines.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import win.Domaines.dto.RoleDTO;
import win.Domaines.entity.Role;
import win.Domaines.service.ErrorLogService;
import win.Domaines.service.RoleService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/roles")
public class RoleController {

    private final RoleService service;
    private final ErrorLogService errorLogService;

    public RoleController(RoleService service, ErrorLogService errorLogService) {
        this.service = service;
        this.errorLogService = errorLogService;
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody RoleDTO dto) {
        try {
            Role saved = service.create(dto);
            return ResponseEntity.ok(RoleDTO.fromEntity(saved));
        } catch (Exception ex) {
            String ticket = errorLogService.logError("Create role failed: " + ex.getMessage(), ex);
            return ResponseEntity.badRequest().body("Error creating role. Ticket: " + ticket);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody RoleDTO dto) {
        try {
            Role updated = service.update(id, dto);
            return ResponseEntity.ok(RoleDTO.fromEntity(updated));
        } catch (Exception ex) {
            String ticket = errorLogService.logError("Update role failed: " + ex.getMessage(), ex);
            return ResponseEntity.badRequest().body("Error updating role. Ticket: " + ticket);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        try {
            service.deleteById(id);
            return ResponseEntity.ok("Role deleted successfully");
        } catch (Exception ex) {
            String ticket = errorLogService.logError("Delete role failed: " + ex.getMessage(), ex);
            return ResponseEntity.badRequest().body("Error deleting role. Ticket: " + ticket);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        try {
            Role r = service.getById(id);
            return ResponseEntity.ok(RoleDTO.fromEntity(r));
        } catch (Exception ex) {
            String ticket = errorLogService.logError("Get role by id failed: " + ex.getMessage(), ex);
            return ResponseEntity.badRequest().body("Error fetching role. Ticket: " + ticket);
        }
    }

    @GetMapping
    public ResponseEntity<List<RoleDTO>> getAll() {
        List<RoleDTO> list = service.getAll().stream()
                .map(RoleDTO::fromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(list);
    }
}
