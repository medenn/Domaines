package win.Domaines.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import win.Domaines.dto.MenuDTO;
import win.Domaines.entity.Menu;
import win.Domaines.service.ErrorLogService;
import win.Domaines.service.MenuService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/menu")
public class MenuController {

    private final MenuService menuService;
    private final ErrorLogService errorLogService;

    public MenuController(MenuService menuService, ErrorLogService errorLogService) {
        this.menuService = menuService;
        this.errorLogService = errorLogService;
    }

    // 🔹 Créer un menu ou un sous-menu
    @PostMapping
    public ResponseEntity<?> create(@RequestBody MenuDTO dto) {
        try {
            Menu saved = menuService.create(dto);
            return ResponseEntity.ok(MenuDTO.fromEntity(saved));
        } catch (Exception ex) {
            String ticket = errorLogService.logError("Create menu failed: " + ex.getMessage(), ex);
            return ResponseEntity.badRequest().body("Error creating menu. Ticket: " + ticket);
        }
    }

    // 🔹 Mettre à jour un menu
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody MenuDTO dto) {
        try {
            Menu updated = menuService.update(id, dto);
            return ResponseEntity.ok(MenuDTO.fromEntity(updated));
        } catch (Exception ex) {
            String ticket = errorLogService.logError("Update menu failed: " + ex.getMessage(), ex);
            return ResponseEntity.badRequest().body("Error updating menu. Ticket: " + ticket);
        }
    }

    // 🔹 Supprimer un menu
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        try {
            menuService.deleteById(id);
            return ResponseEntity.ok("Menu deleted successfully");
        } catch (Exception ex) {
            String ticket = errorLogService.logError("Delete menu failed: " + ex.getMessage(), ex);
            return ResponseEntity.badRequest().body("Error deleting menu. Ticket: " + ticket);
        }
    }

    // 🔹 Récupérer un menu par id
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        try {
            Menu menu = menuService.getById(id);
            return ResponseEntity.ok(MenuDTO.fromEntity(menu));
        } catch (Exception ex) {
            String ticket = errorLogService.logError("Get menu by id failed: " + ex.getMessage(), ex);
            return ResponseEntity.badRequest().body("Error fetching menu. Ticket: " + ticket);
        }
    }

    // 🔹 Récupérer tous les menus
    @GetMapping
    public ResponseEntity<List<MenuDTO>> getAll() {
        List<MenuDTO> list = menuService.getAll()
                .stream()
                .map(MenuDTO::fromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(list);
    }

    // 🔹 Récupérer les sous-menus d’un menu
    @GetMapping("/sousmenus/{parentId}")
    public ResponseEntity<?> getSousMenus(@PathVariable Long parentId) {
        try {
            List<Menu> sousMenus = menuService.getSousMenus(parentId);
            List<MenuDTO> dtoList = sousMenus.stream()
                    .map(MenuDTO::fromEntity)
                    .collect(Collectors.toList());
            return ResponseEntity.ok(dtoList);
        } catch (Exception ex) {
            String ticket = errorLogService.logError("Get sous-menus failed: " + ex.getMessage(), ex);
            return ResponseEntity.badRequest().body("Error fetching sous-menus. Ticket: " + ticket);
        }
    }
}
