package win.Domaines.dto;

import lombok.*;
import win.Domaines.entity.Videos;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VideosDTO {

    private Long id;
    private String titrearabe;
    private String titrefrancais;
    private String descarabe;
    private String descfrancais;
    private byte[] fichier;
    private String lien;

    public static VideosDTO fromEntity(Videos v) {
        return VideosDTO.builder()
                .id(v.getId())
                .titrearabe(v.getTitrearabe())
                .titrefrancais(v.getTitrefrancais())
                .descarabe(v.getDescarabe())
                .descfrancais(v.getDescfrancais())
                .fichier(v.getFichier())
                .lien(v.getLien())
                .build();
    }
}
