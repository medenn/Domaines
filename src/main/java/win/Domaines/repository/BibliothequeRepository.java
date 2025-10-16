package win.Domaines.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import win.Domaines.entity.Bibliotheque;
import win.Domaines.entity.BibliothequeCategorie;

import java.util.List;

public interface BibliothequeRepository extends JpaRepository<Bibliotheque, Long> {

    // Charger toutes les bibliothèques avec leurs fichiers
    @Query("SELECT DISTINCT b FROM Bibliotheque b LEFT JOIN FETCH b.fichiers")
    List<Bibliotheque> findAllWithFichiers();

    // Charger par catégorie avec fichiers
    @Query("SELECT DISTINCT b FROM Bibliotheque b LEFT JOIN FETCH b.fichiers WHERE b.categorie = :categorie")
    List<Bibliotheque> findByCategorieWithFichiers(BibliothequeCategorie categorie);

    // Charger une seule bibliothèque avec fichiers
    @Query("SELECT b FROM Bibliotheque b LEFT JOIN FETCH b.fichiers WHERE b.id = :id")
    Bibliotheque findByIdWithFichiers(Long id);
}
