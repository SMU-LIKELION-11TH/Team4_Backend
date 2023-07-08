package smu.likelion.Traditional.Market.service;

import smu.likelion.Traditional.Market.domain.entity.Category;
import smu.likelion.Traditional.Market.dto.category.CategoryRequestDto;

import java.util.List;

public interface CategoryService {

    public boolean save(CategoryRequestDto categoryRequestDto, Long marketId);

    public List<Category> findByMarketId(Long marketId);

    public boolean update(Long id, CategoryRequestDto categoryRequestDto);

    public void delete(Long id);

}
