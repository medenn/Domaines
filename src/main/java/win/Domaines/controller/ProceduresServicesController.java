package win.Domaines.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import win.Domaines.dto.ProceduresServicesDTO;
import win.Domaines.entity.ProceduresServices;
import win.Domaines.service.ErrorLogService;
import win.Domaines.service.ProceduresServicesService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/procedures-services")
public class ProceduresServicesController {

    private final ProceduresServicesService service;
    private final ErrorLogService errorLogService;

    public ProceduresServicesController(ProceduresServicesService service, ErrorLogService errorLogService) {
        this.service = service;
        this.errorLogService = errorLogService;
    }

    // -------------------------
    // CREATE
    // -------------------------
    @PostMapping
    public ResponseEntity<?> create(@RequestBody ProceduresServicesDTO dto) {
        try {
            ProceduresServices saved = service.create(dto);
            return ResponseEntity.ok(ProceduresServicesDTO.fromEntity(saved));
        } catch (Exception ex) {
            String ticket = errorLogService.logError("Create Procedure/Service failed: " + ex.getMessage(), ex);
            return ResponseEntity.badRequest().body("Erreur lors de la création. Ticket: " + ticket);
        }
    }

    // -------------------------
    // UPDATE
    // -------------------------
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody ProceduresServicesDTO dto) {
        try {
            ProceduresServices updated = service.update(id, dto);
            return ResponseEntity.ok(ProceduresServicesDTO.fromEntity(updated));
        } catch (Exception ex) {
            String ticket = errorLogService.logError("Update Procedure/Service failed: " + ex.getMessage(), ex);
            return ResponseEntity.badRequest().body("Erreur lors de la mise à jour. Ticket: " + ticket);
        }
    }

    // -------------------------
    // DELETE
    // -------------------------
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        try {
            service.deleteById(id);
            return ResponseEntity.ok("Procédure/Service supprimé avec succès");
        } catch (Exception ex) {
            String ticket = errorLogService.logError("Delete Procedure/Service failed: " + ex.getMessage(), ex);
            return ResponseEntity.badRequest().body("Erreur lors de la suppression. Ticket: " + ticket);
        }
    }

    // -------------------------
    // GET ALL (DTO)
    // -------------------------
    @GetMapping
    public ResponseEntity<List<ProceduresServicesDTO>> getAll() {
        List<ProceduresServicesDTO> list = service.getAll()
                .stream()
                .map(ProceduresServicesDTO::fromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(list);
    }

    // -------------------------
    // GET BY ID
    // -------------------------
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        try {
            ProceduresServices p = service.getById(id);
            return ResponseEntity.ok(ProceduresServicesDTO.fromEntity(p));
        } catch (Exception ex) {
            String ticket = errorLogService.logError("Get Procedure/Service by id failed: " + ex.getMessage(), ex);
            return ResponseEntity.badRequest().body("Erreur lors de la récupération. Ticket: " + ticket);
        }
    }

    // -------------------------
    // GET IMAGE (Base64)
    // -------------------------
    @GetMapping("/image/{id}")
    public ResponseEntity<?> getImage(@PathVariable Long id) {
        try {
            String base64 = service.getImageBase64(id);
            return ResponseEntity.ok(base64);
        } catch (Exception ex) {
            String ticket = errorLogService.logError("Get image failed: " + ex.getMessage(), ex);
            return ResponseEntity.badRequest().body("Erreur lors de la récupération de l'image. Ticket: " + ticket);
        }
    }

    // -------------------------
    // GET FICHIER (Base64)
    // -------------------------
    @GetMapping("/fichier/{id}")
    public ResponseEntity<?> getFichier(@PathVariable Long id) {
        try {
            String base64 = service.getFichierBase64(id);
            return ResponseEntity.ok(base64);
        } catch (Exception ex) {
            String ticket = errorLogService.logError("Get fichier failed: " + ex.getMessage(), ex);
            return ResponseEntity.badRequest().body("Erreur lors de la récupération du fichier. Ticket: " + ticket);
        }
    }

}
