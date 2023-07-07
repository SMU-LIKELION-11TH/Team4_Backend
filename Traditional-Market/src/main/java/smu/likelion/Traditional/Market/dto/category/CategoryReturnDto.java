package smu.likelion.Traditional.Market.dto.category;

import lombok.Getter;
import smu.likelion.Traditional.Market.domain.Category;

@Getter
public class CategoryReturnDto {

    private Long id;

    private String categoryName;

    //private List<Store> storeList;

    public CategoryReturnDto(Category category) {
        this.id = category.getId();
        this.categoryName = category.getCategoryName();
    }
}