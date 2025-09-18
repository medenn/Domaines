package win.Domaines.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import win.Domaines.entity.Menu;
import win.Domaines.entity.Messages;

@Repository
public interface MenuRepository extends JpaRepository<Menu, Long> {
}