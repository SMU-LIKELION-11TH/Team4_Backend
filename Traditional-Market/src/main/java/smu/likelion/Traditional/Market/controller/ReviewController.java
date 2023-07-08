package smu.likelion.Traditional.Market.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import smu.likelion.Traditional.Market.dto.review.ReviewRequestDto;
import smu.likelion.Traditional.Market.dto.review.ReviewReturnDto;
import smu.likelion.Traditional.Market.service.ReviewService;

import java.util.List;

@RestController
@RequestMapping("/api/stores")
@RequiredArgsConstructor
public class ReviewController {
    private final ReviewService reviewService;


    @GetMapping("/{storeId}/reviews")
    public ResponseEntity<List<ReviewReturnDto>> getStoreReviewList(@PathVariable Long storeId,
                                                                    @RequestParam String sort) {
        try {
            List<ReviewReturnDto> reviews = reviewService.getStoreReviewList(storeId, sort);
            return ResponseEntity.ok(reviews);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @GetMapping("/reviews/{reviewId}")
    public ResponseEntity<ReviewReturnDto> getReview(@PathVariable Long reviewId) {
        try {
            ReviewReturnDto review = reviewService.getReview(reviewId);
            return ResponseEntity.ok(review);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @PostMapping("{storeId}/review")
    public ResponseEntity<ReviewReturnDto> createReview(@PathVariable Long storeId,
                                                        @RequestBody ReviewRequestDto dto) {
        try {
            ReviewReturnDto review = reviewService.createReview(storeId, dto);
            return ResponseEntity.ok(review);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @PutMapping("{storeId}/reviews/{reviewId}")
    public ResponseEntity<ReviewReturnDto> updateReview(@PathVariable Long storeId,
                                                        @PathVariable Long reviewId,
                                                        @RequestBody ReviewRequestDto dto) {
        try {
            // verify 권한 확인
            ReviewReturnDto review = reviewService.updateReview(reviewId, dto);
            return ResponseEntity.ok(review);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @DeleteMapping("{storeId}/reviews/{reviewId}")
    public ResponseEntity<HttpStatus> deleteReview(@PathVariable Long storeId,
                                                   @PathVariable Long reviewId) {
        try {
            // verify 권한 확인
            reviewService.deleteReview(reviewId);
            ResponseEntity.noContent();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
