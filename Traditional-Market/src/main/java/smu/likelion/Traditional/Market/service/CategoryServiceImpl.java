package smu.likelion.Traditional.Market.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import smu.likelion.Traditional.Market.domain.Category;
import smu.likelion.Traditional.Market.domain.Market;
import smu.likelion.Traditional.Market.dto.category.CategoryRequestDto;
import smu.likelion.Traditional.Market.dto.category.CategoryReturnDto;
import smu.likelion.Traditional.Market.repository.CategoryRepository;
import smu.likelion.Traditional.Market.repository.MarketRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService{

    @Autowired
    private MarketRepository marketRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public boolean save(CategoryRequestDto categoryRequestDto, Long marketId){
        try{
            Optional<Market> market = marketRepository.findById(marketId);
            if(market.isPresent()){
                categoryRepository.save(new Category(categoryRequestDto, market.get()));
                return true;
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<CategoryReturnDto> findByMarketId(Long marketId){
        try{
            List<Category> categoryList = categoryRepository.findByMarketId(marketId);
            return categoryList.stream().map(CategoryReturnDto::new).collect(Collectors.toList());
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean update(Long id, CategoryRequestDto categoryRequestDto){
        try {
            Optional<Category> categoryOptional = categoryRepository.findById(id);
            if(categoryOptional.isPresent()){
                Category category = categoryOptional.get();
                category.setCategoryName(categoryRequestDto.getCategoryName());
                categoryRepository.save(category);
                return true;
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public void delete(Long id){
        try {
            categoryRepository.deleteById(id);
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
