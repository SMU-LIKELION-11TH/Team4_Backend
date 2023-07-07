package smu.likelion.Traditional.Market.jwt;

import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import smu.likelion.Traditional.Market.dto.user.UserRequestDto;

import java.util.*;

@Component
public class JwtTokenProvider {

    private String secretKey;
    private Long validityInMilliseconds;

    private static final String AUTHORITIES_KEY = "auth";

    public JwtTokenProvider(@Value("${security.jwt.token.secret-key}") String secretKey, @Value("${security.jwt.token.expire-length}") Long validityInMilliseconds){
        this.secretKey = secretKey;
        this.validityInMilliseconds = validityInMilliseconds;
    }

    //User를 Map 형태로 바꿔줌(토큰으로 바꾸려면 Map 형태여야 함)
    public Map<String, Object> createPayload(UserRequestDto user){
        Map<String, Object> payloads = new HashMap<>();
        payloads.put("id", user.getId());
        payloads.put("email", user.getEmail());
        payloads.put("role", user.getRole());

        return payloads;
    }

    public String createToken(UserRequestDto user){
        Map<String, Object> payloads = createPayload(user);

        Date now = new Date();

        Date validity = new Date(now.getTime() + validityInMilliseconds);

        return Jwts.builder()
                .setClaims(payloads)
                .setIssuedAt(now)
                .setExpiration(validity)
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    //토큰 해석해서 Map 형태로 내용 추출
    public Map<String, Object> getSubject(String token){
        Claims claims = parseClaims(token);
        return claims;
    }

    //유효성 검사
    public boolean validateToken(String token){
        try {
            Jws<Claims> claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
            if(claims.getBody().getExpiration().before(new Date())){
                return false;
            }
            return true;
        } catch (JwtException | IllegalArgumentException e){
            return false;
        }
    }

    private Claims parseClaims(String token){
        try{
            return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
        } catch (ExpiredJwtException e){
            return e.getClaims();
        }
    }
}
