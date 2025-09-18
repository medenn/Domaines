package win.Domaines.dto;

import lombok.*;
import win.Domaines.entity.BibliothequeCategorie;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BibliothequeCategorieDTO {
    private Long id;
    private String nomarabe;
    private String nomfrancais;

    public static BibliothequeCategorieDTO fromEntity(BibliothequeCategorie entity) {
        BibliothequeCategorieDTO dto = new BibliothequeCategorieDTO();
        dto.setId(entity.getId());
        dto.setNomarabe(entity.getNomarabe());
        dto.setNomfrancais(entity.getNomfrancais());
        return dto;
    }
}
