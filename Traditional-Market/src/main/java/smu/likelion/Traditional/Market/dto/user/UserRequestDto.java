package smu.likelion.Traditional.Market.dto.user;

import lombok.*;
import smu.likelion.Traditional.Market.domain.entity.User;

@Getter @Setter
@NoArgsConstructor
public class UserRequestDto {
    private String nickname;

    @Builder
    public UserRequestDto(String nickname) {
        this.nickname = nickname;
    }

    public User toEntity(String uploadFilename, String saveFilename) {
        return User.builder()
                .nickname(this.nickname)
                .uploadFilename(uploadFilename)
                .saveFilename(saveFilename)
                .build();
    }
}
