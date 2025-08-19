package win.Domaines.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import win.Domaines.entity.TachesMinistere;

@Repository
public interface TachesMinistereRepository extends JpaRepository<TachesMinistere, Long> {
}
