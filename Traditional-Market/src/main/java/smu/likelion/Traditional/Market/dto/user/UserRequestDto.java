package smu.likelion.Traditional.Market.dto.user;

import lombok.Getter;
import smu.likelion.Traditional.Market.domain.enums.Role;

@Getter
public class UserRequestDto {

    private String id;//프론트에선 일단 못 보내줌

    private String email;

    private String password;

    private Role role;
}
