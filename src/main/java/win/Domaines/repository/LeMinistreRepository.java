package win.Domaines.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import win.Domaines.entity.LeMinistre;

@Repository
public interface LeMinistreRepository extends JpaRepository<LeMinistre, Long> {
}
