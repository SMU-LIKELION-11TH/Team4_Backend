package smu.likelion.Traditional.Market.dto.review;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import smu.likelion.Traditional.Market.domain.entity.Review;
import smu.likelion.Traditional.Market.domain.entity.Store;
import smu.likelion.Traditional.Market.domain.entity.User;

@Getter
@Setter
public class ReviewRequestDto {
    private int stars;
    private String content;
    private String email; // spring security

    @Builder
    public ReviewRequestDto(int stars, String content, String email) {
        this.stars = stars;
        this.content = content;
        this.email = email;
    }
    public ReviewRequestDto(){

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
