package smu.likelion.Traditional.Market.dto.user;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import smu.likelion.Traditional.Market.domain.entity.User;
import smu.likelion.Traditional.Market.domain.enums.Role;

@Getter @Setter
@NoArgsConstructor
public class UserLoginReturnDto {

    private Long id;
    private String email;
    private String nickname;
    private Role role;
    private String imageUrl;
    private String createdAt;
    private String updatedAt;
    private String jwt;

    @Builder
    public UserLoginReturnDto(User entity, String jwt) {
        this.id = entity.getId();
        this.email = entity.getEmail();
        this.nickname = entity.getNickname();
        this.role = entity.getRole();
        this.imageUrl = entity.getSaveFilename();
        this.createdAt = entity.getCreatedAt();
        this.updatedAt = entity.getUpdatedAt();
        this.jwt = jwt;
    }
}
