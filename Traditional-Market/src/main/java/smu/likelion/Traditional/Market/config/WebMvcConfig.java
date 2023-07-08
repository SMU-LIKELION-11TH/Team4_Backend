package smu.likelion.Traditional.Market.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import smu.likelion.Traditional.Market.jwt.BearerAuthInterceptor;
import smu.likelion.Traditional.Market.jwt.JwtTokenProvider;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    private final BearerAuthInterceptor bearerAuthInterceptor;
    private final JwtTokenProvider jwtTokenProvider;

    public WebMvcConfig(BearerAuthInterceptor bearerAuthInterceptor, JwtTokenProvider jwtTokenProvider){
        this.bearerAuthInterceptor = bearerAuthInterceptor;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    //인터셉터 실행시킬 경로 설정
    public void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor(bearerAuthInterceptor).addPathPatterns("/api/users");
        registry.addInterceptor(bearerAuthInterceptor).addPathPatterns("/api/markets");
    }
}
