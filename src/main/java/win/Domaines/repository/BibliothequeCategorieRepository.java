package win.Domaines.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import win.Domaines.entity.BibliothequeCategorie;

public interface BibliothequeCategorieRepository extends JpaRepository<BibliothequeCategorie, Long> {
}
