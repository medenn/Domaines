package win.Domaines.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import win.Domaines.entity.ProjetImage;

@Repository
public interface ProjetImageRepository extends JpaRepository<ProjetImage, Long> {
}
