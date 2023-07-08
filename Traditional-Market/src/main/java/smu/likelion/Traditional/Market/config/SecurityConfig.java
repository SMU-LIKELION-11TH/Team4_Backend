package smu.likelion.Traditional.Market.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import smu.likelion.Traditional.Market.jwt.JwtAccessDeniedHandler;
import smu.likelion.Traditional.Market.jwt.JwtAuthenticationEntryPoint;
import smu.likelion.Traditional.Market.jwt.JwtTokenProvider;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtTokenProvider jwtTokenProvider;
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private final JwtAccessDeniedHandler jwtAccessDeniedHandler;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()

                .exceptionHandling()
                .authenticationEntryPoint(jwtAuthenticationEntryPoint) //401: 유저 정보 없이 접근
                .accessDeniedHandler(jwtAccessDeniedHandler) //403: 접근 권한이 없음

                .and()
                .authorizeRequests()
                .antMatchers("/api/login").permitAll()
                .antMatchers(HttpMethod.GET, "/api/markets/**").permitAll()
                .antMatchers(HttpMethod.GET, "/api/category/**").permitAll()
                .antMatchers(HttpMethod.GET, "/api/stores/**").permitAll()
                //.antMatchers("/api/stores/**").hasRole("CEO")
                //.anyRequest().hasRole("ADMIN")
                .anyRequest().permitAll()

                .and()
                .apply(new JwtSecurityConfig(jwtTokenProvider));

        return http.build();
    }
}
