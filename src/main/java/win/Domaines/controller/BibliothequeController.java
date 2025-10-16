package win.Domaines.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import win.Domaines.dto.BibliothequeDTO;
import win.Domaines.dto.BibliothequeFichierDTO;
import win.Domaines.entity.Bibliotheque;
import win.Domaines.service.BibliothequeService;

import java.util.List;

@RestController
@RequestMapping("/api/bibliotheque")
public class BibliothequeController {

    private final BibliothequeService service;

    public BibliothequeController(BibliothequeService service) {
        this.service = service;
    }

    // --- CREATE ---
    @PostMapping
    public ResponseEntity<BibliothequeDTO> create(@RequestBody BibliothequeDTO dto) {
        Bibliotheque b = service.create(dto);
        return ResponseEntity.ok(BibliothequeDTO.fromEntity(b));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BibliothequeDTO> update(@PathVariable Long id, @RequestBody BibliothequeDTO dto) {
        Bibliotheque b = service.update(id, dto);
        return ResponseEntity.ok(BibliothequeDTO.fromEntity(b));
    }


    // --- DELETE ---
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    // --- GET ALL (avec fichiers Base64) ---
    @GetMapping
    public ResponseEntity<List<BibliothequeDTO>> getAll() {
        List<BibliothequeDTO> list = service.getAll();
        return ResponseEntity.ok(list);
    }

    // --- GET BY ID (avec fichiers Base64) ---
    @GetMapping("/{id}")
    public ResponseEntity<BibliothequeDTO> getById(@PathVariable Long id) {
        BibliothequeDTO dto = service.getById(id);
        return ResponseEntity.ok(dto);
    }

    // --- GET BY CATEGORIE (avec fichiers Base64) ---
    @GetMapping("/categorie/{categorieId}")
    public ResponseEntity<List<BibliothequeDTO>> getByCategorie(@PathVariable Long categorieId) {
        List<BibliothequeDTO> list = service.findByCategorie(categorieId);
        return ResponseEntity.ok(list);
    }
}
