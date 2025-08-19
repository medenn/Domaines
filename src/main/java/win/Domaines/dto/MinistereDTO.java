package win.Domaines.dto;

import lombok.*;
import win.Domaines.entity.Ministere;

import java.util.Base64;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MinistereDTO {
    private Long id;
    private String titrearabe;
    private String titrefrancais;
    private String textarabe;
    private String textfrancais;
    private String image; // Pour renvoyer l'image encodée en Base64

    // Conversion depuis l'entité
    public static MinistereDTO fromEntity(Ministere m) {
        return MinistereDTO.builder()
                .id(m.getId())
                .titrearabe(m.getTitrearabe())
                .titrefrancais(m.getTitrefrancais())
                .textarabe(m.getTextarabe())
                .textfrancais(m.getTextfrancais())
                .image(m.getImage() != null ? Base64.getEncoder().encodeToString(m.getImage()) : null)
                .build();
    }
}
