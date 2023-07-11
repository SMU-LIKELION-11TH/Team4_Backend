package smu.likelion.Traditional.Market.jwt;

import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;
import smu.likelion.Traditional.Market.config.auth.AuthUser;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Component
public class JwtTokenProvider {

    private String secretKey;
    private Long validityInMilliseconds;

    private static final String AUTHORITIES_KEY = "role";

    public JwtTokenProvider(@Value("${security.jwt.token.secret-key}") String secretKey, @Value("${security.jwt.token.expire-length}") Long validityInMilliseconds){
        this.secretKey = secretKey;
        this.validityInMilliseconds = validityInMilliseconds;
    }

    //User를 Map 형태로 바꿔줌(토큰으로 바꾸려면 Map 형태여야 함)
    public Map<String, Object> createPayload(AuthUser user){
        Map<String, Object> payloads = new HashMap<>();

        String authority = user.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.joining());

        payloads.put("email", user.getUsername());
        payloads.put(AUTHORITIES_KEY, authority);

        return payloads;
    }

    // Authentication 객체의 권한 정보를 이용해서 토큰을 생성
    public String createToken(Authentication authentication){

        AuthUser user = (AuthUser) authentication.getPrincipal();
        Map<String, Object> payloads = createPayload(user);

        Date now = new Date();
        Date validity = new Date(now.getTime() + validityInMilliseconds);

        return Jwts.builder()
                .setSubject(authentication.getName())
                .setClaims(payloads)
                .setIssuedAt(now)
                .setExpiration(validity)
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    //토큰 해석해서 Map 형태로 내용 추출
    public Map<String, Object> getSubject(String token){
        return parseClaims(token);
    }

    // 토큰 유효성 검사
    public boolean validateToken(String token){
        try {
            Jws<Claims> claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
            // Expiration Time > Current Time
            return !claims.getBody().getExpiration().before(new Date());
        } catch (JwtException | IllegalArgumentException e){
            return false;
        }
    }

    public Authentication getAuthentication(String token){

        Claims claims = parseClaims(token);

        if(claims.get(AUTHORITIES_KEY) == null){
            throw new RuntimeException("권한 정보가 없는 토큰입니다.");
        }

        AuthUser principal = AuthUser.builder()
                .username(claims.get("email").toString())
                .password("")
                .role(claims.get(AUTHORITIES_KEY).toString())
                .build();

        return new UsernamePasswordAuthenticationToken(principal, "", principal.getAuthorities());
    }

    private Claims parseClaims(String token){
        try{
            return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
        } catch (ExpiredJwtException e){
            return e.getClaims();
        }
    }
}
