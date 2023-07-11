package smu.likelion.Traditional.Market.dto.common;

import lombok.*;
import smu.likelion.Traditional.Market.domain.enums.Code;

@Getter @Setter
@ToString
@AllArgsConstructor
public class ReturnDto {
    private final Integer code;
    private final String httpStatus;
    private final String message;
    private Object data;

    public static ReturnDto of(Code code, Object data) {
        return new ReturnDto(code.getCode(), code.getHttpStatus(), code.getMessage(), data);
    }

    public static ReturnDto of(Code code) {
        return new ReturnDto(code.getCode(), code.getHttpStatus(), code.getMessage(), null);
    }
}
