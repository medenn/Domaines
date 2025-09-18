package win.Domaines.dto;

import lombok.*;

import win.Domaines.entity.BibliothequeFichier;
import java.util.Base64;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BibliothequeFichierDTO {

    private Long id;
    private String fichier; // Base64

    public static BibliothequeFichierDTO fromEntity(BibliothequeFichier f) {
        return BibliothequeFichierDTO.builder()
                .id(f.getId())
                .fichier(f.getFichier() != null ? Base64.getEncoder().encodeToString(f.getFichier()) : null)
                .build();
    }
}
