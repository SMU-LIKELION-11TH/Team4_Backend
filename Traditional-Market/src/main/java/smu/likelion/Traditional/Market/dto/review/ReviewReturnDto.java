package smu.likelion.Traditional.Market.dto.review;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import smu.likelion.Traditional.Market.domain.entity.Review;

import java.time.LocalDateTime;

@Getter @Setter
@NoArgsConstructor
public class ReviewReturnDto {
    private Long id;
    private int stars;
    private String content;
    private String reviewer;
    private String storeName;
    private String createdAt;
    private String updatedAt;

    @Builder
    public ReviewReturnDto(Review entity) {
        this.id = entity.getId();
        this.stars = entity.getStars();
        this.content = entity.getContent();
        this.reviewer = entity.getUser().getNickname();
        this.storeName = entity.getStore().getName();
        this.createdAt = entity.getCreatedAt();
        this.updatedAt = entity.getUpdatedAt();
    }
}
