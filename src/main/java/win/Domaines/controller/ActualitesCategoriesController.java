package win.Domaines.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import win.Domaines.dto.ActualitesCategoriesDTO;
import win.Domaines.service.ActualitesCategoriesService;

import java.util.List;

@RestController
@RequestMapping("/api/actualitescategories")
@CrossOrigin(origins = "*")
public class ActualitesCategoriesController {

    private final ActualitesCategoriesService service;

    public ActualitesCategoriesController(ActualitesCategoriesService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<ActualitesCategoriesDTO>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ActualitesCategoriesDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @PostMapping
    public ResponseEntity<ActualitesCategoriesDTO> create(@RequestBody ActualitesCategoriesDTO dto) {
        return ResponseEntity.ok(service.create(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ActualitesCategoriesDTO> update(@PathVariable Long id, @RequestBody ActualitesCategoriesDTO dto) {
        return ResponseEntity.ok(service.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
