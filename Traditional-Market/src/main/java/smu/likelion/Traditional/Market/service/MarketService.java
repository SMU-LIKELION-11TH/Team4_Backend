package smu.likelion.Traditional.Market.service;

import smu.likelion.Traditional.Market.domain.UploadFile;
import smu.likelion.Traditional.Market.dto.market.MarketRequestDto;
import smu.likelion.Traditional.Market.dto.market.MarketReturnDto;

import java.util.List;

public interface MarketService {

    public void save(MarketRequestDto marketRequestDto, UploadFile uploadFile);

    public List<MarketReturnDto> findAll();

    public MarketReturnDto findById(Long id);

    public boolean update(Long id, MarketRequestDto marketRequestDto, UploadFile uploadFile);

    public void delete(Long id);
}
