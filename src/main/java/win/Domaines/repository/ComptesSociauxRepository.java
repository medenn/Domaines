package win.Domaines.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import win.Domaines.entity.ComptesSociaux;

@Repository
public interface ComptesSociauxRepository extends JpaRepository<ComptesSociaux, Long> {
}
