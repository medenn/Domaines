package win.Domaines.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import win.Domaines.entity.ListeTachesMinistere;

@Repository
public interface ListeTachesMinistereRepository extends JpaRepository<ListeTachesMinistere, Long> {

    List<ListeTachesMinistere> findByTachesMinistere_Id(Long tacheid);
    
}
