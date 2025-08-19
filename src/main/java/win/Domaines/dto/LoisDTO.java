package win.Domaines.dto;

import lombok.*;
import win.Domaines.entity.Lois;
import java.time.LocalDate;
import java.util.Base64;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoisDTO {
    private Long id;
    private String titrearabe;
    private String titrefrancais;
    private LocalDate datesortie;
    private String fichierarabe; // Base64
    private String fichierfrancais; // Base64

    public static LoisDTO fromEntity(Lois l) {
        return LoisDTO.builder()
                .id(l.getId())
                .titrearabe(l.getTitrearabe())
                .titrefrancais(l.getTitrefrancais())
                .datesortie(l.getDatesortie())
                .fichierarabe(l.getFichierarabe() != null ? Base64.getEncoder().encodeToString(l.getFichierarabe()) : null)
                .fichierfrancais(l.getFichierfrancais() != null ? Base64.getEncoder().encodeToString(l.getFichierfrancais()) : null)
                .build();
    }
}
