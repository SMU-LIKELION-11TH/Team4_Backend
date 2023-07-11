package smu.likelion.Traditional.Market.domain.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import smu.likelion.Traditional.Market.dto.review.ReviewReturnDto;


import javax.persistence.*;
import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table(name = "reviews")
public class Review extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Integer stars;

    @Column(nullable = false)
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id")
    Store store;

    @Builder
    public Review(Integer stars, String content, User user, Store store) {
        this.stars = stars;
        this.content = content;
        this.user = user;
        this.store = store;
    }

    public static List<ReviewReturnDto> toDtoList(List<Review> entities) {
        // try-catch ?
        return entities.stream().map(
                (review -> ReviewReturnDto.builder().entity(review).build())
        ).collect(Collectors.toList());
    }

    public void update(Integer stars, String content) {
        this.stars = stars;
        this.content = content;
    }
}
