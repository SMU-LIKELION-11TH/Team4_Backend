package smu.likelion.Traditional.Market.dto.user;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import smu.likelion.Traditional.Market.domain.entity.User;
import smu.likelion.Traditional.Market.domain.enums.Role;

@Getter @Setter
public class UserRegister {
    private String email;
    private String password;
    private String nickname;
    private Role role;

    @Builder
    public UserRegister(String email, String password, String nickname, Role role) {
        this.email = email;
        this.password = password;
        this.nickname = nickname;
        this.role = role;
    }

    public User toEntity() {
        return User.builder()
                .email(this.email)
                .password(this.password)
                .nickname(this.nickname)
                .role(this.role)
                .build();
    }
}
