package win.Domaines.dto;

import lombok.*;
import win.Domaines.entity.MotMinistre;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MotMinistreDTO {

private Long id;
private String titrearabe;
private String titrefrancais;
private String textarabe;
private String textfrancais;

public static MotMinistreDTO fromEntity(MotMinistre m) {
    return MotMinistreDTO.builder()
            .id(m.getId())
            .titrearabe(m.getTitrearabe())
            .titrefrancais(m.getTitrefrancais())
            .textarabe(m.getTextarabe())
            .textfrancais(m.getTextfrancais())
            .build();
}


}
