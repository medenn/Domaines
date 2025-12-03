package win.Domaines.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import win.Domaines.entity.Videos;

@Repository
public interface VideosRepository extends JpaRepository<Videos, Long> {
}
