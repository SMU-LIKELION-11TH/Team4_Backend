package smu.likelion.Traditional.Market.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import smu.likelion.Traditional.Market.domain.entity.Category;
import smu.likelion.Traditional.Market.domain.entity.Market;
import smu.likelion.Traditional.Market.dto.category.CategoryRequestDto;
import smu.likelion.Traditional.Market.repository.CategoryRepository;
import smu.likelion.Traditional.Market.repository.MarketRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService{

    @Autowired
    private MarketRepository marketRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public boolean save(CategoryRequestDto categoryRequestDto, Long marketId){
        try{
            Optional<Market> marketOptional = marketRepository.findById(marketId);
            if(marketOptional.isPresent()){
                Market market = marketOptional.get();
                categoryRepository.save(new Category(categoryRequestDto, market));
                return true;
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<Category> findByMarketId(Long marketId){
        try{
            return categoryRepository.findByMarketId(marketId);
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
