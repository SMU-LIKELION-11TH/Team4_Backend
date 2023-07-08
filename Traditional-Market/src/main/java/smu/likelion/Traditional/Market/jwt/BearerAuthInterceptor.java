package smu.likelion.Traditional.Market.jwt;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

//인터셉터: 특정 경로로 요청이 들어오면 컨트롤러보다 먼저 실행됨
//특정 경로는 WebMvcConfig에서 설정
@Component
public class BearerAuthInterceptor implements HandlerInterceptor {

    private AuthorizationExtractor authExtractor;
    private JwtTokenProvider jwtTokenProvider;

    public BearerAuthInterceptor(AuthorizationExtractor authExtractor, JwtTokenProvider jwtTokenProvider){
        this.authExtractor = authExtractor;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object Handler){

        //토큰 추출
        String token = authExtractor.extract(request, "Bearer");

        if(StringUtils.isEmpty(token)){
            return true;
        }

        //유효성 검사
        if(!jwtTokenProvider.validateToken(token)){
            throw new IllegalArgumentException("invalid token");
        }

        //토큰에서 데이터 추출하여 request에 저장
        Map<String, Object> claims = jwtTokenProvider.getSubject(token);
        request.setAttribute("claims", claims);

        return true;
    }
}
