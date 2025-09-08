package win.Domaines.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import win.Domaines.dto.SlidesDTO;
import win.Domaines.entity.Slides;
import win.Domaines.service.SlidesService;
import win.Domaines.service.ErrorLogService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/slides")
public class SlidesController {

    private final SlidesService slidesService;
    private final ErrorLogService errorLogService;

    public SlidesController(SlidesService slidesService, ErrorLogService errorLogService) {
        this.slidesService = slidesService;
        this.errorLogService = errorLogService;
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody SlidesDTO dto) {
        try {
            Slides saved = slidesService.create(dto);
            return ResponseEntity.ok(SlidesDTO.fromEntity(saved));
        } catch (Exception ex) {
            String ticket = errorLogService.logError("Create slide failed: " + ex.getMessage(), ex);
            return ResponseEntity.badRequest().body("Error creating slide. Ticket: " + ticket);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody SlidesDTO dto) {
        try {
            Slides updated = slidesService.update(id, dto);
            return ResponseEntity.ok(SlidesDTO.fromEntity(updated));
        } catch (Exception ex) {
            String ticket = errorLogService.logError("Update slide failed: " + ex.getMessage(), ex);
            return ResponseEntity.badRequest().body("Error updating slide. Ticket: " + ticket);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        try {
            slidesService.deleteById(id);
            return ResponseEntity.ok("Slide deleted successfully");
        } catch (Exception ex) {
            String ticket = errorLogService.logError("Delete slide failed: " + ex.getMessage(), ex);
            return ResponseEntity.badRequest().body("Error deleting slide. Ticket: " + ticket);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        try {
            Slides slide = slidesService.getById(id);
            return ResponseEntity.ok(SlidesDTO.fromEntity(slide));
        } catch (Exception ex) {
            String ticket = errorLogService.logError("Get slide by id failed: " + ex.getMessage(), ex);
            return ResponseEntity.badRequest().body("Error fetching slide. Ticket: " + ticket);
        }
    }

    @GetMapping
    public ResponseEntity<List<SlidesDTO>> getAll() {
        List<SlidesDTO> list = slidesService.getAll()
                .stream()
                .map(SlidesDTO::fromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(list);
    }
}
