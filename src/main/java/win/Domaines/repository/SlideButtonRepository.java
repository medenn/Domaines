package win.Domaines.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import win.Domaines.entity.SlideButton;

@Repository
public interface SlideButtonRepository extends JpaRepository<SlideButton, Long> {
}
