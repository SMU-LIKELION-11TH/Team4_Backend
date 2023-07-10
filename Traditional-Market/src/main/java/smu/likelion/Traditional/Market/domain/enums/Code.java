package smu.likelion.Traditional.Market.domain.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

import java.util.Optional;
import java.util.function.Predicate;

@Getter
@RequiredArgsConstructor
public enum Code {

    OK(200, "OK", "요청에 성공하였습니다."),
    BAD_REQUEST(400, "Bad Request", "잘못된 요청입니다."),
    FORBIDDEN(403, "Forbidden", "권한이 없습니다");


    private final Integer code;
    private final String httpStatus;
    private final String message;

    @Override
    public String toString() {
        return String.format("%s (%d)", this.name(), this.getCode());
    }
}