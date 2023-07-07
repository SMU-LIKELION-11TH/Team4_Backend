package smu.likelion.Traditional.Market.dto.user;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserReturnDto {

    //private Long id;

    private String email;

    private String role; //반환은 문자열로 돼도 괜찮으니까
}
