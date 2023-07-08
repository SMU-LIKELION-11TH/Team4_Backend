package smu.likelion.Traditional.Market.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import smu.likelion.Traditional.Market.domain.entity.StoreImage;

import java.util.List;

@Repository
public interface StoreImageRepository extends JpaRepository<StoreImage,Long> {
    List<StoreImage> findByStore_Id(Long id);
}
