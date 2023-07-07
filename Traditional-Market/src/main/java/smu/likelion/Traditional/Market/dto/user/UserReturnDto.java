package smu.likelion.Traditional.Market.dto.user;

import lombok.*;
import smu.likelion.Traditional.Market.domain.entity.User;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserReturnDto {
    private Long id;
    private String nickname;

    @Builder
    public UserReturnDto(User user) {
        this.id = user.getId();
        this.nickname = user.getNickname();
    }
}
