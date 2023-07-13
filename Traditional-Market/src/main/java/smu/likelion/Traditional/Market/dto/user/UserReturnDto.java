package smu.likelion.Traditional.Market.dto.user;

import lombok.*;
import smu.likelion.Traditional.Market.domain.entity.User;
import smu.likelion.Traditional.Market.domain.enums.Role;

import java.time.LocalDateTime;

@Getter @Setter
@NoArgsConstructor
public class UserReturnDto {
    private Long id;
    private String email;
    private String nickname;
    private Role role;
    private String imageUrl;
    private String createdAt;
    private String updatedAt;
    @Builder
    public UserReturnDto(User entity) {
        this.id = entity.getId();
        this.email = entity.getEmail();
        this.nickname = entity.getNickname();
        this.role = entity.getRole();
        this.imageUrl = entity.getSaveFilename();
        this.createdAt = entity.getCreatedAt();
        this.updatedAt = entity.getUpdatedAt();
    }
}
