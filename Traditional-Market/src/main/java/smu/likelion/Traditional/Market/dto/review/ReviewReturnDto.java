package smu.likelion.Traditional.Market.dto.review;

import lombok.Builder;
import smu.likelion.Traditional.Market.domain.entity.Review;

import java.time.LocalDateTime;

public class ReviewReturnDto {
    private Long id;
    private int stars;
    private String content;
    private String reviewer;
    private LocalDateTime createdAt;

    @Builder
    public ReviewReturnDto(Review entity) {
        this.id = entity.getId();
        this.stars = entity.getStars();
        this.content = entity.getContent();
        this.reviewer = entity.getUser().getNickname();
        this.createdAt = entity.getCreatedAt();
    }
}
