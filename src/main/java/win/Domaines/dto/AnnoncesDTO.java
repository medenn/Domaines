package win.Domaines.dto;

import lombok.*;
import win.Domaines.entity.Annonces;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AnnoncesDTO {
    private Long id;
    private String titrearabe;
    private String titrefrancais;
    private String textarabe;
    private String textfrancais;
    private LocalDateTime datepublication;
    private byte[] image;
    

    public static AnnoncesDTO fromEntity(Annonces a) {
        return AnnoncesDTO.builder()
                .id(a.getId())
                .titrearabe(a.getTitrearabe())
                .titrefrancais(a.getTitrefrancais())
                .textarabe(a.getTextarabe())
                .textfrancais(a.getTextfrancais())
                .datepublication(a.getDatepublication())
                .image(a.getImage())
                .build();
    }
}
