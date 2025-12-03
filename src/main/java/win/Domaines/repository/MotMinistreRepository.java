package win.Domaines.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import win.Domaines.entity.MotMinistre;

@Repository
public interface MotMinistreRepository extends JpaRepository<MotMinistre, Long> {
}
