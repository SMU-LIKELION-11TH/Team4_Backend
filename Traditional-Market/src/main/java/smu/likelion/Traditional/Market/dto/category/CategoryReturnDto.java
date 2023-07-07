package smu.likelion.Traditional.Market.dto.category;

import lombok.Getter;
import smu.likelion.Traditional.Market.domain.entity.Category;
import smu.likelion.Traditional.Market.dto.store.StoreReturnDto;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class CategoryReturnDto {

    private Long id;

    private String categoryName;

    private List<StoreReturnDto> storeList;

    public CategoryReturnDto(Category category) {
        this.id = category.getId();
        this.categoryName = category.getCategoryName();
        this.storeList = category.getStoreList().stream().map(StoreReturnDto::new).collect(Collectors.toList());
    }
}