package win.Domaines.dto;

import lombok.*;
import win.Domaines.entity.TachesMinistere;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TachesMinistereDTO {
    private Long id;
    private String titrearabe;
    private String titrefrancais;

    public static TachesMinistereDTO fromEntity(TachesMinistere t) {
        return TachesMinistereDTO.builder()
                .id(t.getId())
                .titrearabe(t.getTitrearabe())
                .titrefrancais(t.getTitrefrancais())
                .build();
    }
}
