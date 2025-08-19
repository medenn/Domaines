package win.Domaines.dto;

import lombok.*;
import win.Domaines.entity.Permission;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PermissionDTO {
    private Long id;
    private String name;
    private String role;
    private String urlPattern;
    private String methode; // GET, POST, PUT, DELETE, etc.

    public static PermissionDTO fromEntity(Permission p) {
        if (p == null) return null;
        return PermissionDTO.builder()
                .id(p.getId())
                .name(p.getName())
                .role(p.getRole())
                .urlPattern(p.getUrlPattern())
                .methode(p.getMethode())
                .build();
    }
}
