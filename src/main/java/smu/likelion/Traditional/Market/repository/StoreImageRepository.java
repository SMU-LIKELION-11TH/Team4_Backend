package smu.likelion.Traditional.Market.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import smu.likelion.Traditional.Market.domain.StoreImage;

import java.util.List;
import java.util.Optional;

@Repository
public interface StoreImageRepository extends JpaRepository<StoreImage,Long> {
    List<StoreImage> findByStore_Id(Long id);
}
