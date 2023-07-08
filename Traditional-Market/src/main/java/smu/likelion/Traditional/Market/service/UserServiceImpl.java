package smu.likelion.Traditional.Market.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import smu.likelion.Traditional.Market.domain.entity.Review;
import smu.likelion.Traditional.Market.domain.entity.User;
import smu.likelion.Traditional.Market.dto.common.FileDto;
import smu.likelion.Traditional.Market.dto.review.ReviewReturnDto;
import smu.likelion.Traditional.Market.dto.user.*;
import smu.likelion.Traditional.Market.repository.ReviewRepository;
import smu.likelion.Traditional.Market.repository.UserRepository;
import smu.likelion.Traditional.Market.util.ExceptionUtil;
import smu.likelion.Traditional.Market.util.FileStore;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ReviewRepository reviewRepository;
    private final FileStore fileStore;
    // private final PasswordEncoder passwordEncoder;

    private User findUser(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> ExceptionUtil.id(email, User.class.getName()));
    }

    @Override
    @Transactional
    public UserReturnDto login(UserLogin dto) {
        User user = findUser(dto.getEmail());

//        if (!passwordEncoder.matches(dto.getPassword(), user.getPassword())) {
//            throw new BadCredentialsException("Bad credentials");
//        }

        return UserReturnDto.builder()
                .entity(user)
                .build();
    }

    @Override
    @Transactional
    public UserReturnDto getUser(String email) {
        User user = findUser(email);
        return UserReturnDto.builder()
                .entity(user)
                .build();
    }

    @Override
    @Transactional
    public UserReturnDto createUser(UserRegister dto) {
        User user = dto.toEntity();
//        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        return UserReturnDto.builder()
                .entity(userRepository.save(user))
                .build();
    }

    @Override
    @Transactional
    public UserReturnDto updateUser(String email, UserRequestDto dto, MultipartFile multipartFile) {
       User user = findUser(email);

       if (multipartFile != null && dto != null) {
           FileDto file = fileStore.storeFile(multipartFile);
           user.update(dto.getNickname(), file.getUploadFilename(), file.getSaveFilename());
       } else if (multipartFile == null && dto != null){
           user.update(dto.getNickname());
       } else if (multipartFile != null) {
           FileDto file = fileStore.storeFile(multipartFile);
           user.update(file.getUploadFilename(), file.getSaveFilename());
       }

       return UserReturnDto.builder()
               .entity(userRepository.save(user))
               .build();
    }

    @Override
    public void deleteUser(String email) {
        User user = findUser(email);
        reviewRepository.deleteAll(user.getReviews());
        userRepository.delete(user);
    }

    @Override
    public void changePassword(String email, UserPassword dto) {
        User user = findUser(email);

//        if (!passwordEncoder.matches(dto.getOldPassword(), user.getPassword())) {
//            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid Password");
//        }

//        String newPassword = passwordEncoder.encode(dto.getNewPassword());
//        user.setPassword(newPassword);
        userRepository.save(user);
    }

    @Override
    public boolean existEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public List<ReviewReturnDto> getMyReviewList(String email, String sort) {
        User user = findUser(email);

        return Review.toDtoList(reviewRepository.findByUser(user, Sort.by(Sort.Direction.DESC, "stars")));
    }
}
