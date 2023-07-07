package smu.likelion.Traditional.Market.dto.user;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import smu.likelion.Traditional.Market.domain.entity.User;

@Getter @Setter
public class UserRequestDto {
    private String nickname;

    @Builder
    public UserRequestDto(String nickname) {
        this.nickname = nickname;
    }

    public User toEntity(String uploadImg, String storeImg) {
        return User.builder()
                .nickname(this.nickname)
                .uploadImg(uploadImg)
                .storeImg(storeImg)
                .build();
    }

}
