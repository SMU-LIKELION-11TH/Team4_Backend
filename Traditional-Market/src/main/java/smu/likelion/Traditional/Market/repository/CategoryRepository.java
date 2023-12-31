package smu.likelion.Traditional.Market.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import smu.likelion.Traditional.Market.domain.entity.Category;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    List<Category> findByMarketId(Long marketId);

    Optional<Category> findByCategoryName(String name);
}