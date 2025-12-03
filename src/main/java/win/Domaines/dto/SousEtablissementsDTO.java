package win.Domaines.dto;

import lombok.*;
import win.Domaines.entity.SousEtablissements;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SousEtablissementsDTO {

    private Long id;
    private String titrearabe;
    private String titrefrancais;
    private String descarabe;
    private String descfrancais;
    private String lien;
    private byte[] logo;

    public static SousEtablissementsDTO fromEntity(SousEtablissements s) {
        return SousEtablissementsDTO.builder()
                .id(s.getId())
                .titrearabe(s.getTitrearabe())
                .titrefrancais(s.getTitrefrancais())
                .descarabe(s.getDescarabe())
                .descfrancais(s.getDescfrancais())
                .lien(s.getLien())
                .logo(s.getLogo())
                .build();
    }
}
