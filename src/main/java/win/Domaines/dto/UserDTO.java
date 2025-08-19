package win.Domaines.dto;

import lombok.*;
import win.Domaines.entity.User;

import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDTO {
    private Long id;
    private String username;
    private String password; // en entrée uniquement, pas en sortie
    private Set<String> roleNames; // noms des rôles, ex: "ADMIN"

    public static UserDTO fromEntity(User u) {
        if (u == null) return null;
        return UserDTO.builder()
                .id(u.getId())
                .username(u.getUsername())
                .roleNames(u.getRoles() != null ? 
                    u.getRoles().stream().map(r -> r.getName()).collect(Collectors.toSet()) : null)
                .build();
    }
}
