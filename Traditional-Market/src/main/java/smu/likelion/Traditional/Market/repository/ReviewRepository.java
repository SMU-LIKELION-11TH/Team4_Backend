package smu.likelion.Traditional.Market.repository;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import smu.likelion.Traditional.Market.domain.entity.Review;
import smu.likelion.Traditional.Market.domain.entity.Store;
import smu.likelion.Traditional.Market.domain.entity.User;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review,Long> {
    @Query(value = "SELECT avg(review.stars) from Review review JOIN review.store s where s.id = :id")
    Float findAvgReview(@Param("id") Long id);

    @Query(value = "SELECT count(review) from Review review JOIN review.store s where s.id = :id")
    Integer countById(@Param("id") Long id);

    List<Review> findByUser(User user, Sort sort);

    List<Review> findByStore(Store store, Sort sort);

}
