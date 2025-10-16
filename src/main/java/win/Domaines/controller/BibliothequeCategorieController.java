package win.Domaines.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import win.Domaines.dto.BibliothequeCategorieDTO;
import win.Domaines.entity.BibliothequeCategorie;
import win.Domaines.service.BibliothequeCategorieService;

import java.util.List;


@RestController
@RequestMapping("/api/bibliothequecategorie")
public class BibliothequeCategorieController {

    private final BibliothequeCategorieService service;

    public BibliothequeCategorieController(BibliothequeCategorieService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<BibliothequeCategorieDTO> create(@RequestBody BibliothequeCategorieDTO dto) {
        BibliothequeCategorie c = service.create(dto);
        return ResponseEntity.ok(BibliothequeCategorieDTO.fromEntity(c));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BibliothequeCategorieDTO> update(@PathVariable Long id,
            @RequestBody BibliothequeCategorieDTO dto) {
        BibliothequeCategorie c = service.update(id, dto);
        return ResponseEntity.ok(BibliothequeCategorieDTO.fromEntity(c));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<BibliothequeCategorieDTO> getById(@PathVariable Long id) {
        BibliothequeCategorie c = service.getById(id);
        return ResponseEntity.ok(BibliothequeCategorieDTO.fromEntity(c));
    }

    @GetMapping
    public List<BibliothequeCategorieDTO> getAll() {
        return service.getAll().stream()
                .map(BibliothequeCategorieDTO::fromEntity)
                .toList();
    }

}
