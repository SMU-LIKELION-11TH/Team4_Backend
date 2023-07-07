package smu.likelion.Traditional.Market.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import smu.likelion.Traditional.Market.domain.entity.Market;

import java.util.Optional;

public interface MarketRepository extends JpaRepository<Market, Long> {

    public Optional<Market> findByMarketName(String marketName);
}
