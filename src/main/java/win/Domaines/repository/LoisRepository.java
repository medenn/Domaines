package win.Domaines.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import win.Domaines.dto.LoisProjection;
import win.Domaines.entity.Lois;

@Repository
public interface LoisRepository extends JpaRepository<Lois, Long> {

   // Requête pour récupérer uniquement les colonnes utiles
    @Query("SELECT l.id AS id, l.titrearabe AS titrearabe, l.titrefrancais AS titrefrancais, l.datesortie AS datesortie FROM Lois l")
    List<LoisProjection> findAllLight();
}
