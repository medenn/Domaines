package win.Domaines.dto;

import lombok.*;
import win.Domaines.entity.ActualitesCategories;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ActualitesCategoriesDTO {
    private Long id;
    private String nomarabe;
    private String nomfrancais;

    public static ActualitesCategoriesDTO fromEntity(ActualitesCategories entity) {
        return ActualitesCategoriesDTO.builder()
                .id(entity.getId())
                .nomarabe(entity.getNomarabe())
                .nomfrancais(entity.getNomfrancais())
                .build();
    }
}
