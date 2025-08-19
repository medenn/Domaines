package win.Domaines.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import win.Domaines.entity.Actualites;

import java.util.List;

@Repository
public interface ActualitesRepository extends JpaRepository<Actualites, Long> {
    List<Actualites> findByCategorie_Id(Long categorieid);
}
