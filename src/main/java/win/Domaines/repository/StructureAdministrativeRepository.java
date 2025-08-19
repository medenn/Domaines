package win.Domaines.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import win.Domaines.entity.StructureAdministrative;

@Repository
public interface StructureAdministrativeRepository extends JpaRepository<StructureAdministrative, Long> {
}
