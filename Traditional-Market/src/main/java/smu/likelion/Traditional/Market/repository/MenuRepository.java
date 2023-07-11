package smu.likelion.Traditional.Market.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import smu.likelion.Traditional.Market.domain.entity.Menu;

@Repository
public interface MenuRepository extends JpaRepository<Menu,Long> {
}