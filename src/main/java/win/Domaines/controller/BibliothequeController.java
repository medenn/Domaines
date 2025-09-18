package win.Domaines.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import win.Domaines.dto.BibliothequeDTO;
import win.Domaines.entity.Bibliotheque;
import win.Domaines.service.BibliothequeService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/bibliotheque")
public class BibliothequeController {

    private final BibliothequeService service;

    public BibliothequeController(BibliothequeService service) {
        this.service = service;
    }

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

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<BibliothequeDTO> getById(@PathVariable Long id) {
        Bibliotheque b = service.getById(id);
        return ResponseEntity.ok(BibliothequeDTO.fromEntity(b));
    }

    @GetMapping
    public ResponseEntity<List<BibliothequeDTO>> getAll() {
        List<BibliothequeDTO> list = service.getAll()
                .stream()
                .map(BibliothequeDTO::fromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(list);
    }

    @GetMapping("/categorie/{categorieId}")
    public ResponseEntity<List<BibliothequeDTO>> getByCategorie(@PathVariable Long categorieId) {
        List<BibliothequeDTO> list = service.findByBibliothequeCategorie(categorieId)
                .stream()
                .map(BibliothequeDTO::fromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(list);
    }

}
