package smu.likelion.Traditional.Market.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import smu.likelion.Traditional.Market.domain.Store;

import java.util.Optional;

public interface StoreRepository extends JpaRepository<Store,Long> {
    Optional<Store> findByStoreName(String storeName);

}
