package smu.likelion.Traditional.Market.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import smu.likelion.Traditional.Market.dto.user.UserRequestDto;
import smu.likelion.Traditional.Market.dto.user.UserReturnDto;
import smu.likelion.Traditional.Market.jwt.JwtTokenProvider;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api")
public class UserController {

    private final JwtTokenProvider jwtTokenProvider;

    public UserController(JwtTokenProvider jwtTokenProvider){
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/login")
    public String login(@RequestBody UserRequestDto userRequestDto){
        //db에서 id 찾아서 userRequestDto에 넣어주기
        return jwtTokenProvider.createToken(userRequestDto);
    }

    @GetMapping("/users")
    public ResponseEntity<UserReturnDto> getInfo(HttpServletRequest request){
        Map<String, Object> claims = (Map<String, Object>) request.getAttribute("claims");

        UserReturnDto userReturnDto = new UserReturnDto();
        userReturnDto.setEmail((String) claims.get("email"));
        userReturnDto.setRole((String) claims.get("role"));

        return ResponseEntity.ok().body(userReturnDto);
    }
}
