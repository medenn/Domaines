package win.Domaines.dto;

import lombok.*;
import win.Domaines.entity.ProceduresServices;

import java.util.Base64;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProceduresServicesDTO {

    private Long id;
    private String titrearabe;
    private String titrefrancais;
    private String textarabe;
    private String textfrancais;
    private String image;   // Base64
    private String fichier; // Base64

    public static ProceduresServicesDTO fromEntity(ProceduresServices p) {
        return ProceduresServicesDTO.builder()
                .id(p.getId())
                .titrearabe(p.getTitrearabe())
                .titrefrancais(p.getTitrefrancais())
                .textarabe(p.getTextarabe())
                .textfrancais(p.getTextfrancais())
                .image(p.getImage() != null ? Base64.getEncoder().encodeToString(p.getImage()) : null)
                .fichier(p.getFichier() != null ? Base64.getEncoder().encodeToString(p.getFichier()) : null)
                .build();
    }
}
