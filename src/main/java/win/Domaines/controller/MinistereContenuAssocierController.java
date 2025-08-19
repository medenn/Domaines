package win.Domaines.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import win.Domaines.dto.MinistereContenuAssocierDTO;
import win.Domaines.entity.MinistereContenuAssocier;
import win.Domaines.service.ErrorLogService;
import win.Domaines.service.MinistereContenuAssocierService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/ministerecontenuassocier")
public class MinistereContenuAssocierController {

    private final MinistereContenuAssocierService service;
    private final ErrorLogService errorLogService;

    public MinistereContenuAssocierController(MinistereContenuAssocierService service, ErrorLogService errorLogService) {
        this.service = service;
        this.errorLogService = errorLogService;
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody MinistereContenuAssocierDTO dto) {
        try {
            MinistereContenuAssocier saved = service.create(dto);
            return ResponseEntity.ok(MinistereContenuAssocierDTO.fromEntity(saved));
        } catch (Exception ex) {
            String ticket = errorLogService.logError("Création contenu associé échouée: " + ex.getMessage(), ex);
            return ResponseEntity.badRequest().body("Erreur création contenu associé. Ticket: " + ticket);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody MinistereContenuAssocierDTO dto) {
        try {
            MinistereContenuAssocier updated = service.update(id, dto);
            return ResponseEntity.ok(MinistereContenuAssocierDTO.fromEntity(updated));
        } catch (Exception ex) {
            String ticket = errorLogService.logError("Mise à jour contenu associé échouée: " + ex.getMessage(), ex);
            return ResponseEntity.badRequest().body("Erreur mise à jour contenu associé. Ticket: " + ticket);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        try {
            service.deleteById(id);
            return ResponseEntity.ok("Contenu associé supprimé avec succès");
        } catch (Exception ex) {
            String ticket = errorLogService.logError("Suppression contenu associé échouée: " + ex.getMessage(), ex);
            return ResponseEntity.badRequest().body("Erreur suppression contenu associé. Ticket: " + ticket);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        try {
            MinistereContenuAssocier m = service.getById(id);
            return ResponseEntity.ok(MinistereContenuAssocierDTO.fromEntity(m));
        } catch (Exception ex) {
            String ticket = errorLogService.logError("Récupération contenu associé échouée: " + ex.getMessage(), ex);
            return ResponseEntity.badRequest().body("Erreur récupération contenu associé. Ticket: " + ticket);
        }
    }

    @GetMapping
    public ResponseEntity<List<MinistereContenuAssocierDTO>> getAll() {
        List<MinistereContenuAssocierDTO> list = service.getAll()
                .stream()
                .map(MinistereContenuAssocierDTO::fromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(list);
    }
}
