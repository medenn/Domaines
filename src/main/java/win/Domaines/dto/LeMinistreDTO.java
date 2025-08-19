package win.Domaines.dto;

import lombok.*;
import win.Domaines.entity.LeMinistre;
import java.util.Base64;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LeMinistreDTO {
    private Long id;
    private String titrearabe;
    private String titrefrancais;
    private String textarabe;
    private String textfrancais;
    private String image;     // Encodée en Base64
    private String cvarabe;   // Encodé en Base64
    private String cvfrancais; // Encodé en Base64

    public static LeMinistreDTO fromEntity(LeMinistre m) {
        return LeMinistreDTO.builder()
                .id(m.getId())
                .titrearabe(m.getTitrearabe())
                .titrefrancais(m.getTitrefrancais())
                .textarabe(m.getTextarabe())
                .textfrancais(m.getTextfrancais())
                .image(m.getImage() != null ? Base64.getEncoder().encodeToString(m.getImage()) : null)
                .cvarabe(m.getCvarabe() != null ? Base64.getEncoder().encodeToString(m.getCvarabe()) : null)
                .cvfrancais(m.getCvfrancais() != null ? Base64.getEncoder().encodeToString(m.getCvfrancais()) : null)
                .build();
    }
}
