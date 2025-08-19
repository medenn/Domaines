package win.Domaines.service;

import org.springframework.stereotype.Service;
import win.Domaines.dto.RoleDTO;
import win.Domaines.entity.Role;
import win.Domaines.exception.ResourceNotFoundException;
import win.Domaines.repository.RoleRepository;

import java.util.List;

@Service
public class RoleService {

    private final RoleRepository roleRepo;

    public RoleService(RoleRepository roleRepo) {
        this.roleRepo = roleRepo;
    }

    public Role create(RoleDTO dto) {
        Role r = new Role();
        r.setName(dto.getName());
        return roleRepo.save(r);
    }

    public Role update(Long id, RoleDTO dto) {
        Role r = roleRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Role not found with id: " + id));
        r.setName(dto.getName());
        return roleRepo.save(r);
    }

    public void deleteById(Long id) {
        if (!roleRepo.existsById(id)) {
            throw new ResourceNotFoundException("Role not found with id: " + id);
        }
        roleRepo.deleteById(id);
    }

    public Role getById(Long id) {
        return roleRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Role not found with id: " + id));
    }

    public List<Role> getAll() {
        return roleRepo.findAll();
    }
}
