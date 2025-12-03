package win.Domaines.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import win.Domaines.entity.SousEtablissements;

@Repository
public interface SousEtablissementsRepository extends JpaRepository<SousEtablissements, Long> {
}
