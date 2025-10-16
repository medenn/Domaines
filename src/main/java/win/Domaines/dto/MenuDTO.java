package win.Domaines.dto;

import lombok.*;
import win.Domaines.entity.Menu;

import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MenuDTO {
    private Long id;
    private String nomarabe;
    private String nomfrancais;
    private String lien;
    private Integer ordre;
    private Boolean etat;
    private Long parentId; // pour rattacher à un menu parent
    private List<Long> enfantsIds; // liste des sous-menus (IDs uniquement)

    public static MenuDTO fromEntity(Menu menu) {
        return MenuDTO.builder()
                .id(menu.getId())
                .nomarabe(menu.getNomarabe())
                .nomfrancais(menu.getNomfrancais())
                .lien(menu.getLien())
                .ordre(menu.getOrdre())
                .etat(menu.getEtat())
                .parentId(menu.getParent() != null ? menu.getParent().getId() : null)
                .build();
    }
}
