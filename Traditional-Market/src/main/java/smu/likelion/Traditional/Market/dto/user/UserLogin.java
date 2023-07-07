package smu.likelion.Traditional.Market.dto.user;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UserLogin {
    private String email;
    private String password;

    @Builder
    public UserLogin(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
