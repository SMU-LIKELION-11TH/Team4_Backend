package smu.likelion.Traditional.Market.service;

import smu.likelion.Traditional.Market.dto.category.CategoryReturnDto;

import java.util.List;

public interface CategoryService {

    public List<CategoryReturnDto> findByMarketId(Long marketId);

}
