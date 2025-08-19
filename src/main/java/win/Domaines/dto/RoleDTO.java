package win.Domaines.dto;

import lombok.*;
import win.Domaines.entity.Role;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RoleDTO {
    private Long id;
    private String name;

    public static RoleDTO fromEntity(Role r) {
        if (r == null) return null;
        return RoleDTO.builder()
                .id(r.getId())
                .name(r.getName())
                .build();
    }
}
