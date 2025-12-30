package win.Domaines.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import win.Domaines.entity.Projets;

@Repository
public interface ProjetsRepository extends JpaRepository<Projets, Long> {

    @Query("SELECT p FROM Projets p LEFT JOIN FETCH p.images")
    List<Projets> findAllWithImages();

    @Query("SELECT p FROM Projets p LEFT JOIN FETCH p.images WHERE p.id = :id")
Projets findByIdWithImages(Long id);


}
