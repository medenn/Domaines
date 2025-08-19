package win.Domaines.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import win.Domaines.dto.PermissionDTO;
import win.Domaines.service.ErrorLogService;
import win.Domaines.service.PermissionService;

import java.util.List;

@RestController
@RequestMapping("/api/permissions")
public class PermissionController {

    private final PermissionService permissionService;
    private final ErrorLogService errorLogService;

    public PermissionController(PermissionService permissionService, ErrorLogService errorLogService) {
        this.permissionService = permissionService;
        this.errorLogService = errorLogService;
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody PermissionDTO dto) {
        try {
            PermissionDTO result = permissionService.create(dto);
            return ResponseEntity.ok(result);
        } catch (Exception ex) {
            String ticket = errorLogService.logError("Create permission failed: " + ex.getMessage(), ex);
            return ResponseEntity.badRequest().body("Error creating permission. Ticket: " + ticket);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody PermissionDTO dto) {
        try {
            PermissionDTO result = permissionService.update(id, dto);
            return ResponseEntity.ok(result);
        } catch (Exception ex) {
            String ticket = errorLogService.logError("Update permission failed: " + ex.getMessage(), ex);
            return ResponseEntity.badRequest().body("Error updating permission. Ticket: " + ticket);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        try {
            permissionService.delete(id);
            return ResponseEntity.ok("Permission deleted");
        } catch (Exception ex) {
            String ticket = errorLogService.logError("Delete permission failed: " + ex.getMessage(), ex);
            return ResponseEntity.badRequest().body("Error deleting permission. Ticket: " + ticket);
        }
    }

    @GetMapping
    public ResponseEntity<List<PermissionDTO>> list() {
        return ResponseEntity.ok(permissionService.listAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable Long id) {
        try {
            PermissionDTO dto = permissionService.getById(id);
            return ResponseEntity.ok(dto);
        } catch (Exception ex) {
            String ticket = errorLogService.logError("Get permission failed: " + ex.getMessage(), ex);
            return ResponseEntity.status(404).body("Not found. Ticket: " + ticket);
        }
    }
}
