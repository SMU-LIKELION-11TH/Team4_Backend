package smu.likelion.Traditional.Market.dto.review;

import lombok.Builder;
import smu.likelion.Traditional.Market.domain.entity.Review;
import smu.likelion.Traditional.Market.domain.entity.Store;
import smu.likelion.Traditional.Market.domain.entity.User;

public class ReviewRequestDto {
    private int stars;
    private String content;
    private Long storeId;
    private Long userId;

    @Builder
    public ReviewRequestDto(int stars, String content, Long storeId, Long userId) {
        this.stars = stars;
        this.content = content;
        this.storeId = storeId;
        this.userId = userId;
    }

    public Review toEntity(Store store, User user) {
        return Review.builder()
                .stars(this.stars)
                .content(this.content)
                .user(user)
                .store(store)
                .build();
    }
}
