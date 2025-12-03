package win.Domaines.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import win.Domaines.entity.Projets;

@Repository
public interface ProjetsRepository extends JpaRepository<Projets, Long> {
}
