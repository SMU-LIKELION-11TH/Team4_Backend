package smu.likelion.Traditional.Market.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import smu.likelion.Traditional.Market.config.auth.AuthUtil;
import smu.likelion.Traditional.Market.domain.entity.Review;
import smu.likelion.Traditional.Market.domain.entity.Store;
import smu.likelion.Traditional.Market.domain.entity.User;
import smu.likelion.Traditional.Market.dto.review.ReviewRequestDto;
import smu.likelion.Traditional.Market.dto.review.ReviewReturnDto;
import smu.likelion.Traditional.Market.repository.ReviewRepository;
import smu.likelion.Traditional.Market.repository.StoreRepository;
import smu.likelion.Traditional.Market.repository.UserRepository;
import smu.likelion.Traditional.Market.util.ExceptionUtil;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private final StoreRepository storeRepository;
    private final UserRepository userRepository;
    private final ReviewRepository reviewRepository;

    private User findUser(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> ExceptionUtil.id(email, User.class.getName()));
    }

    private Store findStore(Long storeId) {
        return storeRepository.findById(storeId)
                .orElseThrow(() -> ExceptionUtil.id(storeId, Store.class.getName()));
    }

    private Review findReview(Long reviewId) {
        return reviewRepository.findById(reviewId).orElseThrow(
                () -> ExceptionUtil.id(reviewId, Store.class.getName())
        );
    }

    @Override
    @Transactional
    public List<ReviewReturnDto> getStoreReviewList(Long storeId, String sort) {
        Store store = findStore(storeId);

        if ("stars".equals(sort)) {
            return Review.toDtoList(reviewRepository.findByStore(store, Sort.by(Sort.Direction.DESC, "stars")));
        }

        return Review.toDtoList(reviewRepository.findByStore(store, Sort.by(Sort.Direction.DESC, "id")));
    }

    @Override
    @Transactional
    public ReviewReturnDto getReview(Long reviewId) {

        Review review = findReview(reviewId);

        return ReviewReturnDto.builder()
                .entity(review)
                .build();
    }

    @Override
    @Transactional
    public ReviewReturnDto createReview(Long storeId, ReviewRequestDto dto) {
        User user = findUser(AuthUtil.getAuthUser()); // spring security
        Store store = findStore(storeId);

        Review review = dto.toEntity(store, user);
        user.addReview(review);
        store.addReview(review);

        Review reviewEntity = reviewRepository.save(review);

        store.updateAvgReview(reviewRepository.findAvgReview(storeId));
        store.updateCntReview(reviewRepository.countById(storeId));

        storeRepository.save(store);

        return ReviewReturnDto.builder()
                .entity(reviewEntity)
                .build();
    }

    @Override
    @Transactional
    public ReviewReturnDto updateReview(Long reviewId, ReviewRequestDto dto) {

        Review review = findReview(reviewId);
        review.update(dto.getStars(), dto.getContent());

        return ReviewReturnDto.builder()
                .entity(reviewRepository.save(review))
                .build();
    }

    @Override
    @Transactional
    public void deleteReview(Long reviewId) {
        // User, Store ?
        reviewRepository.deleteById(reviewId);
    }

    @Override
    public Long getReviewerId(Long reviewId){
        Optional<Review> reviewOptional = reviewRepository.findById(reviewId);
        if(reviewOptional.isPresent()){
            return reviewOptional.get().getUser().getId();
        }
        return null;
    }
}
