package win.Domaines.dto;

import lombok.*;
import win.Domaines.entity.MinistereContenuAssocier;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MinistereContenuAssocierDTO {
    private Long id;
    private String titrearabe;
    private String titrefrancais;
    private String textarabe;
    private String textfrancais;

    public static MinistereContenuAssocierDTO fromEntity(MinistereContenuAssocier m) {
        return MinistereContenuAssocierDTO.builder()
                .id(m.getId())
                .titrearabe(m.getTitrearabe())
                .titrefrancais(m.getTitrefrancais())
                .textarabe(m.getTextarabe())
                .textfrancais(m.getTextfrancais())
                .build();
    }
}
