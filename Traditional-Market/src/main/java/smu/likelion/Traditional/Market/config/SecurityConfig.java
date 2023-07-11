package smu.likelion.Traditional.Market.config;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.support.OpenEntityManagerInViewFilter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;
import smu.likelion.Traditional.Market.config.auth.AuthUserDetailsService;
import smu.likelion.Traditional.Market.jwt.JwtAccessDeniedHandler;
import smu.likelion.Traditional.Market.jwt.JwtAuthenticationEntryPoint;
import smu.likelion.Traditional.Market.jwt.JwtTokenProvider;
import smu.likelion.Traditional.Market.repository.UserRepository;

@Configuration
@EnableWebSecurity // Spring Security 활성화
@RequiredArgsConstructor
public class SecurityConfig {

    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private final JwtAccessDeniedHandler jwtAccessDeniedHandler;


    /**
     * permitAll : 인증, 권한 X 가능
     * authenticated : 인증 해야 됨
     * antMatchers("경로", "경로", ... ) 가능
     * */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()

                .exceptionHandling()
                .authenticationEntryPoint(jwtAuthenticationEntryPoint) //401: 유저 정보 없이 접근
                .accessDeniedHandler(jwtAccessDeniedHandler) //403: 접근 권한이 없음

                .and()
                .headers()
                .frameOptions()
                .sameOrigin()

                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)

                .and()
                .authorizeRequests()
                .antMatchers("/api/login").permitAll()
                .antMatchers("/api/register").permitAll()
                .antMatchers("/h2-console/**").permitAll()
//                // review 생성, 수정, 삭제 -> only ROLE_USER
//                .antMatchers(HttpMethod.POST,"/api/stores/**/review").hasAnyRole("USER", "ADMIN")
//                .antMatchers(HttpMethod.PUT,"/api/stores/**/reviews/**").hasRole("USER")
//                .antMatchers(HttpMethod.DELETE,"/api/stores/**/reviews/**").hasRole("USER")
//                // store & menu 생성, 수정, 삭제 -> only ROLE_CEO
//                .antMatchers(HttpMethod.POST, "/api/stores", "/api/stores/**/menus").hasRole("CEO")
//                .antMatchers(HttpMethod.PUT, "/api/stores/**", "/api/stores/**/menu").hasRole("CEO")
//                .antMatchers(HttpMethod.DELETE, "/api/stores/**", "/api/stores/**/menu").hasRole("CEO")
                //.anyRequest().authenticated()
                .anyRequest().authenticated()

                // Session 사용 X
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)

                .and()
                .apply(new JwtSecurityConfig(jwtTokenProvider));


        return http.build();
    }
    @Bean
    public FilterRegistrationBean<OpenEntityManagerInViewFilter> openEntityManagerInViewFilter() {
        FilterRegistrationBean<OpenEntityManagerInViewFilter> filterFilterRegistrationBean = new FilterRegistrationBean<>();
        filterFilterRegistrationBean.setFilter(new OpenEntityManagerInViewFilter());
        filterFilterRegistrationBean.setOrder(Integer.MIN_VALUE); // 예시를 위해 최우선 순위로 Filter 등록
        return filterFilterRegistrationBean;
    }
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Bean
    public UserDetailsService userDetailsService() { return new AuthUserDetailsService(userRepository); }

}
