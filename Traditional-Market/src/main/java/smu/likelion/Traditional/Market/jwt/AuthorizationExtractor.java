package smu.likelion.Traditional.Market.jwt;

import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

//프론트에서 보낸 "Bearer 토큰"의 Bearer를 때서 토큰 부분만 추출해줌
@Component
public class AuthorizationExtractor {

    public static final String AUTHORIZATION = "Authorization";

    public String extract(HttpServletRequest request, String type){
        Enumeration<String> headers = request.getHeaders(AUTHORIZATION);
        while(headers.hasMoreElements()){
            String value = headers.nextElement();
            if(value.toLowerCase().startsWith(type.toLowerCase())){
                return value.substring(type.length()).trim();
            }
        }

        return Strings.EMPTY;
    }

}
