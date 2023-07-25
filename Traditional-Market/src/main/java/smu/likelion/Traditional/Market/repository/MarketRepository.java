package smu.likelion.Traditional.Market.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import smu.likelion.Traditional.Market.domain.entity.Market;

import java.util.Optional;
@Repository
public interface MarketRepository extends JpaRepository<Market, Long> {

    public Optional<Market> findByMarketName(String marketName);
}