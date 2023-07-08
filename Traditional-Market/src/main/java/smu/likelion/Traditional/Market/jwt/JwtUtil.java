package smu.likelion.Traditional.Market.jwt;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * security 적용 전에 사용했던 클래스입니다.
 * 현재로써는 사용하는 곳이 없는데, 이후에 필요 없다고 판단되면 삭제하도록 하겠습니다.
 * */
@Component
public class JwtUtil {

    private static AuthorizationExtractor authExtractor;
    private static JwtTokenProvider jwtTokenProvider;

    public JwtUtil(AuthorizationExtractor authExtractor, JwtTokenProvider jwtTokenProvider){
        JwtUtil.authExtractor = authExtractor;
        JwtUtil.jwtTokenProvider = jwtTokenProvider;
    }

    public static String getBody(HttpServletRequest request){

        //토큰 추출
        String token = authExtractor.extract(request, "Bearer");

        if(StringUtils.isEmpty(token)){
            throw new IllegalArgumentException("token is null");
        }

        //유효성 검사
        if(!jwtTokenProvider.validateToken(token)){
            throw new IllegalArgumentException("invalid token");
        }

        //토큰에서 데이터 추출
        Map<String, Object> claims = jwtTokenProvider.getSubject(token);

        return (String) claims.get("role");
    }
}
