package win.Domaines.dto;

import lombok.*;
import win.Domaines.entity.ListeTachesMinistere;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ListeTachesMinistereDTO {
    private Long id;
    private Long tacheid; // id de TachesMinistere
    private String titrearabe;
    private String titrefrancais;

    public static ListeTachesMinistereDTO fromEntity(ListeTachesMinistere l) {
        return ListeTachesMinistereDTO.builder()
                .id(l.getId())
                .tacheid(l.getTachesMinistere() != null ? l.getTachesMinistere().getId() : null)
                .titrearabe(l.getTitrearabe())
                .titrefrancais(l.getTitrefrancais())
                .build();
    }
}
