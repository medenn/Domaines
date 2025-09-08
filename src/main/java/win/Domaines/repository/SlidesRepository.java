package win.Domaines.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import win.Domaines.entity.Slides;

@Repository
public interface SlidesRepository extends JpaRepository<Slides, Long> {
}
