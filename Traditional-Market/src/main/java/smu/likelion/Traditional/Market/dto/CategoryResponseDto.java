package smu.likelion.Traditional.Market.dto;

import lombok.Getter;
import smu.likelion.Traditional.Market.domain.Category;

@Getter
public class CategoryResponseDto {

    private Long id;

    private String category_name;

    public CategoryResponseDto(Category category) {
        this.id = category.getId();
        this.category_name = category.getCategoryName();
    }
}
