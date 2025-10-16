package win.Domaines.dto;

import java.time.LocalDate;

// Interface de projection pour récupérer uniquement les infos sans fichiers
public interface LoisProjection {
    Long getId();
    String getTitrearabe();
    String getTitrefrancais();
    LocalDate getDatesortie();
}
