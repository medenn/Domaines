package win.Domaines.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import win.Domaines.entity.MinistereContenuAssocier;

@Repository
public interface MinistereContenuAssocierRepository extends JpaRepository<MinistereContenuAssocier, Long> {
}
