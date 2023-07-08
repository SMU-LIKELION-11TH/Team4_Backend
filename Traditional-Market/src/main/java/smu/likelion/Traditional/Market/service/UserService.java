package smu.likelion.Traditional.Market.service;


import org.springframework.web.multipart.MultipartFile;
import smu.likelion.Traditional.Market.dto.review.ReviewReturnDto;
import smu.likelion.Traditional.Market.dto.user.*;

import java.util.List;

public interface UserService {
    public UserReturnDto login(UserLogin dto);
    public UserReturnDto getUser(String email);
    public UserReturnDto createUser(UserRegister dto);
    public UserReturnDto updateUser(String email, UserRequestDto dto, MultipartFile multipartFile);
    public void deleteUser(String email);
    public void changePassword(String email, UserPassword dto);
    public boolean existEmail(String email);
    public List<ReviewReturnDto> getMyReviewList(String email, String sort);
}
