package smu.likelion.Traditional.Market.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import smu.likelion.Traditional.Market.domain.enums.Code;
import smu.likelion.Traditional.Market.dto.common.ReturnDto;
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
    public ResponseEntity<ReturnDto> getStoreReviewList(@PathVariable Long storeId,
                                                        @RequestParam(required = false) String sort) {
        try {
            return ResponseEntity.ok(ReturnDto.of(Code.OK, reviewService.getStoreReviewList(storeId, sort)));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @GetMapping("/reviews/{reviewId}")
    public ResponseEntity<ReturnDto> getReview(@PathVariable Long reviewId) {
        try {
            return ResponseEntity.ok(ReturnDto.of(Code.OK, reviewService.getReview(reviewId)));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @PostMapping("{storeId}/review")
    public ResponseEntity<ReturnDto> createReview(@PathVariable Long storeId,
                                                  @RequestBody ReviewRequestDto dto) {
        try {
            return ResponseEntity.ok(ReturnDto.of(Code.OK, reviewService.createReview(storeId, dto)));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @PutMapping("{storeId}/reviews/{reviewId}")
    public ResponseEntity<ReturnDto> updateReview(@PathVariable Long storeId,
                                                  @PathVariable Long reviewId,
                                                  @RequestBody ReviewRequestDto dto) {
        try {
            return ResponseEntity.ok(ReturnDto.of(Code.OK, reviewService.updateReview(reviewId, dto)));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @DeleteMapping("{storeId}/reviews/{reviewId}")
    public ResponseEntity<ReturnDto> deleteReview(@PathVariable Long storeId,
                                                  @PathVariable Long reviewId) {
        try {
            reviewService.deleteReview(reviewId);
            return ResponseEntity.ok(ReturnDto.of(Code.OK));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
