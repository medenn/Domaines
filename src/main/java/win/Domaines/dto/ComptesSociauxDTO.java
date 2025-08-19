package win.Domaines.dto;

import lombok.*;
import win.Domaines.entity.ComptesSociaux;
import java.util.Base64;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ComptesSociauxDTO {
    private Long id;
    private String nom;
    private String lien;
    private String icon; // Encodé en Base64

    public static ComptesSociauxDTO fromEntity(ComptesSociaux c) {
        return ComptesSociauxDTO.builder()
                .id(c.getId())
                .nom(c.getNom())
                .lien(c.getLien())
                .icon(c.getIcon() != null ? Base64.getEncoder().encodeToString(c.getIcon()) : null)
                .build();
    }
}
