package smu.likelion.Traditional.Market.service;

import smu.likelion.Traditional.Market.dto.market.MarketRequestDto;
import smu.likelion.Traditional.Market.dto.market.MarketReturnDto;

import java.util.List;

public interface MarketService {

    public void save(MarketRequestDto marketRequestDto);

    public List<MarketReturnDto> findAll();

    public MarketReturnDto findById(Long id);

    public boolean update(Long id, MarketRequestDto marketRequestDto);

    public void delete(Long id);
}
