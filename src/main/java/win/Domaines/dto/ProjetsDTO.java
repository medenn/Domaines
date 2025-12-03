package win.Domaines.dto;

import lombok.*;
import win.Domaines.entity.Projets;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProjetsDTO {

    private Long id;
    private String titrearabe;
    private String titrefrancais;
    private String descarabe;
    private String descfrancais;
    private String lien;
    private List<ProjetImageDTO> images;

    public static ProjetsDTO fromEntity(Projets p) {
        return ProjetsDTO.builder()
                .id(p.getId())
                .titrearabe(p.getTitrearabe())
                .titrefrancais(p.getTitrefrancais())
                .descarabe(p.getDescarabe())
                .descfrancais(p.getDescfrancais())
                .lien(p.getLien())
                .images(p.getImages().stream().map(ProjetImageDTO::fromEntity).collect(Collectors.toList()))
                .build();
    }
}
