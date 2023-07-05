package smu.likelion.Traditional.Market.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import smu.likelion.Traditional.Market.domain.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
