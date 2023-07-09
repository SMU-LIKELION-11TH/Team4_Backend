package smu.likelion.Traditional.Market.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import smu.likelion.Traditional.Market.dto.review.ReviewReturnDto;
import smu.likelion.Traditional.Market.dto.user.*;
import smu.likelion.Traditional.Market.jwt.JwtTokenProvider;
import smu.likelion.Traditional.Market.service.UserService;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<UserReturnDto> register(@RequestBody UserRegister dto) {
        try {
            UserReturnDto user = userService.createUser(dto);
            return ResponseEntity.ok(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UserLogin dto) {
        try {
            String jwt = userService.login(dto);
            return ResponseEntity.ok(jwt);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @GetMapping("/user")
    public ResponseEntity<UserReturnDto> getUser() {
        try {
            UserReturnDto user = userService.getUser();
            return ResponseEntity.ok(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @PutMapping(value = "/user", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<UserReturnDto> updateUser(@RequestPart(value = "data", required = false) UserRequestDto dto,
                                                    @RequestPart(value = "file", required = false) MultipartFile multipartFile) {
        try {
            UserReturnDto user = userService.updateUser(dto, multipartFile);
            return ResponseEntity.ok(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @DeleteMapping("/user")
    public ResponseEntity<HttpStatus> deleteUser() {
        try {
            userService.deleteUser();
            ResponseEntity.noContent();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @PutMapping("/user/password")
    public ResponseEntity<HttpStatus> changePassword(@RequestBody UserPassword dto) {
        try {
            userService.changePassword(dto);
            ResponseEntity.noContent();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @GetMapping("/user/exist-email/{email}")
    public Boolean existByEmail(@PathVariable String email) {
        try {
            return userService.existEmail(email);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @GetMapping("/user/reviews")
    public ResponseEntity<List<ReviewReturnDto>> getMyReviewList(@RequestParam(required = false) String sort) {
        try {
            List<ReviewReturnDto> reviews = userService.getMyReviewList(sort);
            return ResponseEntity.ok(reviews);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
