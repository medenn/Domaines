package win.Domaines.service;

import org.springframework.stereotype.Service;
import win.Domaines.dto.PermissionDTO;
import win.Domaines.entity.Permission;
import win.Domaines.exception.ResourceNotFoundException;
import win.Domaines.repository.PermissionRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PermissionService {

    private final PermissionRepository permissionRepository;

    public PermissionService(PermissionRepository permissionRepository) {
        this.permissionRepository = permissionRepository;
    }

    public PermissionDTO create(PermissionDTO dto) {
        Permission p = new Permission();
        p.setName(dto.getName());
        p.setRole(dto.getRole());
        p.setUrlPattern(dto.getUrlPattern());
        p.setMethode(dto.getMethode());
        p = permissionRepository.save(p);
        dto.setId(p.getId());
        return dto;
    }

    public PermissionDTO update(Long id, PermissionDTO dto) {
        Permission p = permissionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Permission not found with id " + id));
        p.setName(dto.getName());
        p.setRole(dto.getRole());
        p.setUrlPattern(dto.getUrlPattern());
        p.setMethode(dto.getMethode());
        p = permissionRepository.save(p);
        dto.setId(p.getId());
        return dto;
    }

    public void delete(Long id) {
        Permission p = permissionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Permission not found with id " + id));
        permissionRepository.delete(p);
    }

    public List<PermissionDTO> listAll() {
        return permissionRepository.findAll().stream()
                .map(PermissionDTO::fromEntity)
                .collect(Collectors.toList());
    }

    public PermissionDTO getById(Long id) {
        Permission p = permissionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Permission not found with id " + id));
        return PermissionDTO.fromEntity(p);
    }
}
