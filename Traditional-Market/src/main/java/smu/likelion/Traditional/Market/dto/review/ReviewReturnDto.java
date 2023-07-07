package smu.likelion.Traditional.Market.dto.review;

import smu.likelion.Traditional.Market.domain.entity.Review;

import java.time.LocalDateTime;

public class ReviewReturnDto {
    private Long id;
    private int stars;
    private String content;
    private String reviewer;
    private LocalDateTime createdAt;

    public ReviewReturnDto(Review review) {
        this.id = review.getId();
        this.stars = review.getStars();
        this.content = review.getContent();
        this.reviewer = review.getUser().getNickname();
        this.createdAt = review.getCreatedAt();
    }
}
