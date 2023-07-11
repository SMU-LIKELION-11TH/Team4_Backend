package smu.likelion.Traditional.Market.service;

import smu.likelion.Traditional.Market.dto.review.ReviewRequestDto;
import smu.likelion.Traditional.Market.dto.review.ReviewReturnDto;

import java.util.List;

public interface ReviewService {
    public List<ReviewReturnDto> getStoreReviewList(Long storeId, String sort);

    public ReviewReturnDto getReview(Long reviewId);

    public ReviewReturnDto createReview(Long storeId, ReviewRequestDto dto);

    public ReviewReturnDto updateReview(Long reviewId, ReviewRequestDto dto);

    public void deleteReview(Long reviewId);

    public Long getReviewerId(Long reviewId);
}
