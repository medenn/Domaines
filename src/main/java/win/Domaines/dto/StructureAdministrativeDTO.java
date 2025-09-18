package win.Domaines.dto;

import lombok.*;
import win.Domaines.entity.StructureAdministrative;

import java.util.Base64;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StructureAdministrativeDTO {
    private Long id;
    private String titrearabe;
    private String titrefrancais;
    private String fichierarabe;    // Encodé Base64
    private String fichierfrancais; // Encodé Base64
    private String textarabe;
    private String textfrancais;

    public static StructureAdministrativeDTO fromEntity(StructureAdministrative s) {
        return StructureAdministrativeDTO.builder()
                .id(s.getId())
                .titrearabe(s.getTitrearabe())
                .titrefrancais(s.getTitrefrancais())
                .fichierarabe(s.getFichierarabe() != null ? Base64.getEncoder().encodeToString(s.getFichierarabe()) : null)
                .fichierfrancais(s.getFichierfrancais() != null ? Base64.getEncoder().encodeToString(s.getFichierfrancais()) : null)
                .textarabe(s.getTextArabe())
                .textfrancais(s.getTextFrancais())
                .build();
    }
}
