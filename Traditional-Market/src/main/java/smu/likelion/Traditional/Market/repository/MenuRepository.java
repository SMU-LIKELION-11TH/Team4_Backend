package smu.likelion.Traditional.Market.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import smu.likelion.Traditional.Market.domain.Menu;

public interface MenuRepository extends JpaRepository<Menu,Long> {
}
