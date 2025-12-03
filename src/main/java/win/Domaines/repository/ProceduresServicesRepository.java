package win.Domaines.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import win.Domaines.entity.ProceduresServices;

import java.util.List;

@Repository
public interface ProceduresServicesRepository extends JpaRepository<ProceduresServices, Long> {
    
}
