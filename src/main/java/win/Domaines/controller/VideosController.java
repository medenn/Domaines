package win.Domaines.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import win.Domaines.dto.VideosDTO;
import win.Domaines.entity.Videos;
import win.Domaines.service.VideosService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/videos")
public class VideosController {

    private final VideosService service;

    public VideosController(VideosService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<VideosDTO> create(@RequestBody VideosDTO dto) {
        Videos saved = service.create(dto);
        return ResponseEntity.ok(VideosDTO.fromEntity(saved));
    }

    @PutMapping("/{id}")
    public ResponseEntity<VideosDTO> update(@PathVariable Long id,
                                            @RequestBody VideosDTO dto) {
        Videos updated = service.update(id, dto);
        return ResponseEntity.ok(VideosDTO.fromEntity(updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.ok("Vidéo supprimée avec succès");
    }

    @GetMapping("/{id}")
    public ResponseEntity<VideosDTO> getById(@PathVariable Long id) {
        Videos v = service.getById(id);
        return ResponseEntity.ok(VideosDTO.fromEntity(v));
    }

    @GetMapping
    public ResponseEntity<List<VideosDTO>> getAll() {
        List<VideosDTO> list = service.getAll()
                .stream()
                .map(VideosDTO::fromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(list);
    }
}
