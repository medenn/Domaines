package win.Domaines.dto;

import lombok.*;
import win.Domaines.entity.Bibliotheque;

import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BibliothequeDTO {

    private Long id;
    private String descriptionarabe;
    private String descriptionfrancais;
    private List<String> fichiers; // Base64
    private Long categorieId;
    private Integer ordre;
    private Boolean etat;

    public static BibliothequeDTO fromEntity(Bibliotheque b) {
        List<String> fichiersBase64 = null;
        if (b.getFichiers() != null) {
            fichiersBase64 = b.getFichiers().stream()
                    .map(f -> f.getFichier() != null ? Base64.getEncoder().encodeToString(f.getFichier()) : null)
                    .collect(Collectors.toList());
        }

        return BibliothequeDTO.builder()
                .id(b.getId())
                .descriptionarabe(b.getDescriptionarabe())
                .descriptionfrancais(b.getDescriptionfrancais())
                .fichiers(fichiersBase64)
                .categorieId(b.getCategorie() != null ? b.getCategorie().getId() : null)
                .ordre(b.getOrdre())
                .etat(b.getEtat())
                .build();
    }
}
