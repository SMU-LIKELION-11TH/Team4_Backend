package smu.likelion.Traditional.Market.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import smu.likelion.Traditional.Market.domain.entity.Review;
import smu.likelion.Traditional.Market.domain.entity.User;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findByUser(User user);
}
