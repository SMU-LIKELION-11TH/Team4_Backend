package smu.likelion.Traditional.Market.repository;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import smu.likelion.Traditional.Market.domain.entity.Store;
import smu.likelion.Traditional.Market.domain.entity.User;

import java.util.List;
import java.util.Optional;

@Repository

public interface StoreRepository extends JpaRepository<Store,Long> {
    Optional<Store> findByStoreName(String storeName);

    List<Store> findAllByCategoryId(Long id, Sort sort);
    List<Store> findByUser(User user);
}

