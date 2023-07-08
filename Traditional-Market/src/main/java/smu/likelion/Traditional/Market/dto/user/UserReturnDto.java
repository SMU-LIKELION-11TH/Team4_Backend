package smu.likelion.Traditional.Market.dto.user;

import lombok.*;
import smu.likelion.Traditional.Market.domain.entity.User;
import smu.likelion.Traditional.Market.domain.enums.Role;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserReturnDto {
    private Long id;
    private String email;
    private String nickname;
    private Role role;

    // 이미지, apiKey 추가

    @Builder
    public UserReturnDto(User entity) {
        this.id = entity.getId();
        this.email = entity.getEmail();
        this.nickname = entity.getNickname();
        this.role = entity.getRole();
    }
}
