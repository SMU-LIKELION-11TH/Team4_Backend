package smu.likelion.Traditional.Market.dto.user;

import lombok.*;

@Getter @Setter
@NoArgsConstructor
public class UserPassword {
    private String oldPassword;
    private String newPassword;

    @Builder
    public UserPassword(String oldPassword, String newPassword) {
        this.oldPassword = oldPassword;
        this.newPassword = newPassword;
    }
}
