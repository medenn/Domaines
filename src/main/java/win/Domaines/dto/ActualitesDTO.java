package win.Domaines.dto;

import lombok.*;
import win.Domaines.entity.Actualites;

import java.time.LocalDateTime;
import java.util.Base64;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ActualitesDTO {
    private Long id;
    private String titrearabe;
    private String titrefrancais;
    private String textarabe;
    private String textfrancais;
    private String image; // Base64
    private LocalDateTime datepublication;
    private Long categorieid;
     private String lienar;
    private String lienfr;

    public static ActualitesDTO fromEntity(Actualites a) {
        return ActualitesDTO.builder()
                .id(a.getId())
                .titrearabe(a.getTitrearabe())
                .titrefrancais(a.getTitrefrancais())
                .textarabe(a.getTextarabe())
                .lienar(a.getLienar())
                .lienfr(a.getLienfr())
                .textfrancais(a.getTextfrancais())
                .image(a.getImage() != null ? Base64.getEncoder().encodeToString(a.getImage()) : null)
                .datepublication(a.getDatepublication())
                .categorieid(a.getCategorie() != null ? a.getCategorie().getId() : null)
                .build();
    }
}
