package smu.likelion.Traditional.Market.dto.category;

import lombok.Getter;
import smu.likelion.Traditional.Market.domain.Category;

@Getter
public class CategoryReturnDto {

    private Long id;

    private String category_name;

    public CategoryReturnDto(Category category) {
        this.id = category.getId();
        this.category_name = category.getCategoryName();
    }
}