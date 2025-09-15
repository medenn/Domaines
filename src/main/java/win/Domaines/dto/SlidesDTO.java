package win.Domaines.dto;

import lombok.*;
import win.Domaines.entity.Slides;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SlidesDTO {
    private Long id;
    private String titrearabe;
    private String titrefrancais;
    private String textarabe;
    private String textfrancais;
    private String image; // Encodé en Base64
    private boolean etat;
    private Integer ordre;
    private List<SlideButtonDTO> buttons;

    public static SlidesDTO fromEntity(Slides s) {
        if (s == null) return null;
        return SlidesDTO.builder()
                .id(s.getId())
                .titrearabe(s.getTitreArabe())
                .titrefrancais(s.getTitreFrancais())
                .textarabe(s.getTextArabe())
                .textfrancais(s.getTextFrancais())
                .image(s.getImage() != null ? Base64.getEncoder().encodeToString(s.getImage()) : null)
                .etat(s.isEtat())
                .ordre(s.getOrdre())
                .buttons(s.getButtons() != null
                        ? s.getButtons().stream().map(SlideButtonDTO::fromEntity).collect(Collectors.toList())
                        : null)
                .build();
    }
}
