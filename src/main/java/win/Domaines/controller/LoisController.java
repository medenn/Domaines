package win.Domaines.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import win.Domaines.dto.LoisDTO;
import win.Domaines.dto.LoisProjection;
import win.Domaines.entity.Lois;
import win.Domaines.service.ErrorLogService;
import win.Domaines.service.LoisService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/lois")
public class LoisController {

    private final LoisService service;
    private final ErrorLogService errorLogService;

    public LoisController(LoisService service, ErrorLogService errorLogService) {
        this.service = service;
        this.errorLogService = errorLogService;
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody LoisDTO dto) {
        try {
            Lois saved = service.create(dto);
            return ResponseEntity.ok(LoisDTO.fromEntity(saved));
        } catch (Exception ex) {
            String ticket = errorLogService.logError("Create lois failed: " + ex.getMessage(), ex);
            return ResponseEntity.badRequest().body("Erreur lors de la création. Ticket: " + ticket);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody LoisDTO dto) {
        try {
            Lois updated = service.update(id, dto);
            return ResponseEntity.ok(LoisDTO.fromEntity(updated));
        } catch (Exception ex) {
            String ticket = errorLogService.logError("Update lois failed: " + ex.getMessage(), ex);
            return ResponseEntity.badRequest().body("Erreur lors de la mise à jour. Ticket: " + ticket);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        try {
            service.deleteById(id);
            return ResponseEntity.ok("Lois supprimée avec succès");
        } catch (Exception ex) {
            String ticket = errorLogService.logError("Delete lois failed: " + ex.getMessage(), ex);
            return ResponseEntity.badRequest().body("Erreur lors de la suppression. Ticket: " + ticket);
        }
    }

    // 🔹 getAll rapide avec projection
    @GetMapping("/rapide")
    public ResponseEntity<?> getAll2() {
        try {
            List<LoisProjection> list = service.getAllLight();
            return ResponseEntity.ok(list);
        } catch (Exception ex) {
            String ticket = errorLogService.logError("Get all lois failed: " + ex.getMessage(), ex);
            return ResponseEntity.badRequest().body("Erreur lors de la récupération de la liste. Ticket: " + ticket);
        }
    }


      // 🔹 getAll 
   @GetMapping
    public ResponseEntity<List<LoisDTO>> getAll() {
        List<LoisDTO> list = service.getAll()
                .stream()
                .map(LoisDTO::fromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(list);
    }

    
    // 🔹 getById simple (sans fichiers)
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        try {
            Lois l = service.getById(id);
            return ResponseEntity.ok(LoisDTO.fromEntity(l));
        } catch (Exception ex) {
            String ticket = errorLogService.logError("Get lois by id failed: " + ex.getMessage(), ex);
            return ResponseEntity.badRequest().body("Erreur lors de la récupération. Ticket: " + ticket);
        }
    }

   // 🔹 Récupérer fichier arabe en Base64
@GetMapping("/fichierarabe/{id}")
public ResponseEntity<?> getFichierArabe(@PathVariable Long id) {
    try {
        String base64 = service.getFichierArabeBase64(id);
        return ResponseEntity.ok(base64);
    } catch (Exception ex) {
        String ticket = errorLogService.logError("Get fichier arabe failed: " + ex.getMessage(), ex);
        return ResponseEntity.badRequest().body("Erreur lors de la récupération du fichier arabe. Ticket: " + ticket);
    }
}

// 🔹 Récupérer fichier français en Base64
@GetMapping("/fichierfrancais/{id}")
public ResponseEntity<?> getFichierFrancais(@PathVariable Long id) {
    try {
        String base64 = service.getFichierFrancaisBase64(id);
        return ResponseEntity.ok(base64);
    } catch (Exception ex) {
        String ticket = errorLogService.logError("Get fichier français failed: " + ex.getMessage(), ex);
        return ResponseEntity.badRequest().body("Erreur lors de la récupération du fichier français. Ticket: " + ticket);
    }
}

}
