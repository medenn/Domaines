package win.Domaines.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import win.Domaines.entity.Lois;

@Repository
public interface LoisRepository extends JpaRepository<Lois, Long> {
}
