package win.Domaines.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import win.Domaines.entity.Menu;
import win.Domaines.dto.MenuDTO;
import win.Domaines.repository.MenuRepository;

import java.util.List;

@Service
public class MenuService {

    private final MenuRepository repository;

    public MenuService(MenuRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public Menu create(MenuDTO dto) {
        Menu menu = new Menu();
        menu.setNomarabe(dto.getNomarabe());
        menu.setNomfrancais(dto.getNomfrancais());
        menu.setLien(dto.getLien());
        menu.setOrdre(dto.getOrdre());
        menu.setEtat(dto.getEtat());

        if (dto.getParentId() != null) {
            Menu parent = repository.findById(dto.getParentId())
                    .orElseThrow(() -> new RuntimeException("Menu parent non trouvé"));
            menu.setParent(parent);
        }

        return repository.save(menu);
    }

    @Transactional
    public Menu update(Long id, MenuDTO dto) {
        Menu menu = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Menu non trouvé"));

        menu.setNomarabe(dto.getNomarabe());
        menu.setNomfrancais(dto.getNomfrancais());
        menu.setLien(dto.getLien());
        menu.setOrdre(dto.getOrdre());
        menu.setEtat(dto.getEtat());

        if (dto.getParentId() != null) {
            Menu parent = repository.findById(dto.getParentId())
                    .orElseThrow(() -> new RuntimeException("Menu parent non trouvé"));
            menu.setParent(parent);
        } else {
            menu.setParent(null);
        }

        return repository.save(menu);
    }

    @Transactional
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public Menu getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Menu non trouvé"));
    }

    @Transactional(readOnly = true)
    public List<Menu> getAll() {
        return repository.findAll();
    }

    // 🔹 Nouvelle méthode pour récupérer les sous-menus d’un menu
    @Transactional(readOnly = true)
    public List<Menu> getSousMenus(Long parentId) {
        Menu parent = repository.findById(parentId)
                .orElseThrow(() -> new RuntimeException("Menu parent non trouvé"));
        return repository.findAll().stream()
                .filter(m -> m.getParent() != null && m.getParent().getId().equals(parent.getId()))
                .toList();
    }
}
