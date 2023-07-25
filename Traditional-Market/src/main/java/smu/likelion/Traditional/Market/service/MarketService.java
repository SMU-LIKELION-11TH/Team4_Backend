package smu.likelion.Traditional.Market.service;

import smu.likelion.Traditional.Market.domain.entity.Market;
import smu.likelion.Traditional.Market.dto.market.MarketRequestDto;

import java.util.List;
import java.util.Optional;

public interface MarketService {

    public void save(MarketRequestDto marketRequestDto);

    public List<Market> findAll();

    public Optional<Market> findById(Long id);

    public Optional<Market> findByMarketName(String marketName);

    public boolean update(Long id, MarketRequestDto marketRequestDto);

    public void delete(Long id);
}
