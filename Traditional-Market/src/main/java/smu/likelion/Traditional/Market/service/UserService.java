package smu.likelion.Traditional.Market.service;


import org.springframework.web.multipart.MultipartFile;
import smu.likelion.Traditional.Market.dto.review.ReviewReturnDto;
import smu.likelion.Traditional.Market.dto.user.*;

import java.util.List;

public interface UserService {
    public UserLoginReturnDto login(UserLogin dto);
    public UserReturnDto getUser();
    public UserReturnDto createUser(UserRegister dto);
    public UserReturnDto updateUser(UserRequestDto dto, MultipartFile multipartFile);
    public void deleteUser();
    public void changePassword(UserPassword dto);
    public boolean existEmail(String email);
    public List<ReviewReturnDto> getMyReviewList(String sort);
}
