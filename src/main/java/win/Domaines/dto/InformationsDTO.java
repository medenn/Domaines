package win.Domaines.dto;

import lombok.*;
import win.Domaines.entity.Informations;
import java.util.Base64;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InformationsDTO {
    private Long id;
    private String nomsite;
    private String logo; // Encodé en Base64
    private String adresse;
    private String telephone;
    private String email;
    private String horaires;

    public static InformationsDTO fromEntity(Informations i) {
        return InformationsDTO.builder()
                .id(i.getId())
                .nomsite(i.getNomsite())
                .logo(i.getLogo() != null ? Base64.getEncoder().encodeToString(i.getLogo()) : null)
                .adresse(i.getAdresse())
                .telephone(i.getTelephone())
                .email(i.getEmail())
                .horaires(i.getHoraires())
                .build();
    }
}
