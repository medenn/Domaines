package win.Domaines.dto;

import lombok.*;
import win.Domaines.entity.SlideButton;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SlideButtonDTO {
    private Long id;
    private String titrearabe;
    private String titrefrancais;
    private String lien;

    public static SlideButtonDTO fromEntity(SlideButton b) {
        if (b == null) return null;
        return SlideButtonDTO.builder()
                .id(b.getId())
                .titrearabe(b.getTitreArabe())
                .titrefrancais(b.getTitreFrancais())
                .lien(b.getLien())
                .build();
    }
}
