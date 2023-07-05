package smu.likelion.Traditional.Market.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import smu.likelion.Traditional.Market.domain.Market;

public interface MarketRepository extends JpaRepository<Market, Long> {
}
