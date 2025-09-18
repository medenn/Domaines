package win.Domaines.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import win.Domaines.entity.Bibliotheque;
import win.Domaines.entity.BibliothequeCategorie;

public interface BibliothequeRepository extends JpaRepository<Bibliotheque, Long> {
    List<Bibliotheque> findByCategorie(BibliothequeCategorie categorie);
}
