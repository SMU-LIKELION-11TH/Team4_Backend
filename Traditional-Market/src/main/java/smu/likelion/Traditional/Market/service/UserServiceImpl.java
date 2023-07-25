package smu.likelion.Traditional.Market.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import smu.likelion.Traditional.Market.config.auth.AuthUtil;
import smu.likelion.Traditional.Market.domain.entity.Review;
import smu.likelion.Traditional.Market.domain.entity.User;
import smu.likelion.Traditional.Market.dto.common.FileDto;
import smu.likelion.Traditional.Market.dto.review.ReviewReturnDto;
import smu.likelion.Traditional.Market.dto.store.StoreReturnDto;
import smu.likelion.Traditional.Market.dto.user.*;
import smu.likelion.Traditional.Market.jwt.JwtFilter;
import smu.likelion.Traditional.Market.jwt.JwtTokenProvider;
import smu.likelion.Traditional.Market.repository.ReviewRepository;
import smu.likelion.Traditional.Market.repository.StoreRepository;
import smu.likelion.Traditional.Market.repository.UserRepository;
import smu.likelion.Traditional.Market.util.ExceptionUtil;
import smu.likelion.Traditional.Market.util.FileStore;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ReviewRepository reviewRepository;
    private final StoreRepository storeRepository;
    private final FileStore fileStore;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider tokenProvider;
    private final AuthenticationManagerBuilder authenticationManager;

    private User findUser(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> ExceptionUtil.id(email, User.class.getName()));
    }

    @Override
    @Transactional
    public UserLoginReturnDto login(UserLogin dto) {

        User user = findUser(dto.getEmail());
        // Password Check
        if (!passwordEncoder.matches(dto.getPassword(), user.getPassword())) {
            throw new BadCredentialsException("Bad credentials");
        }

        // try Authenticate
        UsernamePasswordAuthenticationToken token =
                new UsernamePasswordAuthenticationToken(dto.getEmail(), dto.getPassword());

        Authentication authentication = authenticationManager.getObject().authenticate(token);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = tokenProvider.createToken(authentication);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(JwtFilter.AUTHORIZATION_HEADER, "Bearer " + jwt);

        return UserLoginReturnDto.builder()
                .entity(user)
                .jwt(jwt)
                .build();
    }

    @Override
    @Transactional
    public UserReturnDto getUser() {
        User user = findUser(AuthUtil.getAuthUser());

        return UserReturnDto.builder()
                .entity(user)
                .build();
    }

    @Override
    @Transactional
    public UserReturnDto createUser(UserRegister dto) {
        User user = dto.toEntity();
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        return UserReturnDto.builder()
                .entity(userRepository.save(user))
                .build();
    }

    @Override
    @Transactional
    public UserReturnDto updateUser(UserRequestDto dto, MultipartFile multipartFile) {
       User user = findUser(AuthUtil.getAuthUser());

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
    @Transactional
    public void deleteUser() {
        User user = findUser(AuthUtil.getAuthUser());
        reviewRepository.deleteAll(user.getReviews());
        userRepository.delete(user);
    }

    @Override
    @Transactional
    public void changePassword(UserPassword dto) {
        User user = findUser(AuthUtil.getAuthUser());

        if (!passwordEncoder.matches(dto.getOldPassword(), user.getPassword())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid Password");
        }

        String newPassword = passwordEncoder.encode(dto.getNewPassword());
        user.setPassword(newPassword);
        userRepository.save(user);
    }

    @Override
    @Transactional
    public boolean existEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    @Transactional
    public List<ReviewReturnDto> getMyReviewList(String sort) {
        User user = findUser(AuthUtil.getAuthUser());

        return Review.toDtoList(reviewRepository.findByUser(user, Sort.by(Sort.Direction.DESC, "stars")));
    }

    @Override
    @Transactional
    public List<StoreReturnDto> getMyStoreList() {
        User user = findUser(AuthUtil.getAuthUser());
        return storeRepository.findByUser(user)
                .stream().map(StoreReturnDto::new).collect(Collectors.toList());
    }
}
