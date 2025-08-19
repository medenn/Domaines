package win.Domaines.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import win.Domaines.dto.UserDTO;
import win.Domaines.entity.Role;
import win.Domaines.entity.User;
import win.Domaines.exception.ResourceNotFoundException;
import win.Domaines.repository.RoleRepository;
import win.Domaines.repository.UserRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepo;
    private final RoleRepository roleRepo;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepo, RoleRepository roleRepo, PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.roleRepo = roleRepo;
        this.passwordEncoder = passwordEncoder;
    }

    public UserDTO create(UserDTO dto) {
        User user = new User();
        user.setUsername(dto.getUsername());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));

        Set<Role> roles = dto.getRoleNames().stream()
                .map(rn -> roleRepo.findByName(rn)
                        .orElseThrow(() -> new RuntimeException("Role not found: " + rn)))
                .collect(Collectors.toSet());
        user.setRoles(roles);

        User saved = userRepo.save(user);
        dto.setId(saved.getId());
        dto.setPassword(null);
        return dto;
    }

    public List<UserDTO> listAll() {
        return userRepo.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public UserDTO getById(Long id) {
        return userRepo.findById(id)
                .map(this::mapToDTO)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
    }

    public UserDTO update(Long id, UserDTO dto) {
        User u = userRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));

        u.setUsername(dto.getUsername());

        if (dto.getPassword() != null && !dto.getPassword().isEmpty()) {
            u.setPassword(passwordEncoder.encode(dto.getPassword()));
        }

        if (dto.getRoleNames() != null) {
            Set<Role> roles = dto.getRoleNames().stream()
                    .map(rn -> roleRepo.findByName(rn)
                            .orElseThrow(() -> new RuntimeException("Role not found: " + rn)))
                    .collect(Collectors.toSet());
            u.setRoles(roles);
        }

        User updated = userRepo.save(u);
        return mapToDTO(updated);
    }

    public void deleteById(Long id) {
        if (!userRepo.existsById(id)) {
            throw new RuntimeException("User not found with id: " + id);
        }
        userRepo.deleteById(id);
    }

    private UserDTO mapToDTO(User user) {
        UserDTO dto = new UserDTO();
        dto.setId(user.getId());
        dto.setUsername(user.getUsername());
        dto.setRoleNames(user.getRoles().stream()
                .map(Role::getName)
                .collect(Collectors.toSet()));
        return dto;
    }
}
