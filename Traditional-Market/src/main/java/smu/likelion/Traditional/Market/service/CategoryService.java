package smu.likelion.Traditional.Market.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import smu.likelion.Traditional.Market.domain.Category;
import smu.likelion.Traditional.Market.dto.CategoryResponseDto;
import smu.likelion.Traditional.Market.repository.CategoryRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public List<CategoryResponseDto> findByMarketId(Long marketId){
        try{
            List<Category> categoryList = categoryRepository.findByMarketId(marketId);
            return categoryList.stream().map(CategoryResponseDto::new).collect(Collectors.toList());
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
