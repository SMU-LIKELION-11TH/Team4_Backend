package smu.likelion.Traditional.Market.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import smu.likelion.Traditional.Market.dto.review.ReviewReturnDto;
import smu.likelion.Traditional.Market.dto.user.*;
import smu.likelion.Traditional.Market.service.UserService;

import java.util.List;

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

    @PostMapping("/login")
    public ResponseEntity<UserReturnDto> login(@RequestBody UserLogin dto) {
        try {
            UserReturnDto user = userService.login(dto);
            return ResponseEntity.ok(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @GetMapping("/user/{email}")
    public ResponseEntity<UserReturnDto> getUser(@PathVariable String email) {
        try {
            UserReturnDto user = userService.getUser(email);
            return ResponseEntity.ok(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @PutMapping("/user/{email}")
    public ResponseEntity<UserReturnDto> updateUser(@PathVariable String email,
                                                    @RequestBody UserRequestDto dto) {
        try {
            UserReturnDto user = userService.updateUser(email, dto);
            return ResponseEntity.ok(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @DeleteMapping("/user/{email}")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable String email) {
        try {
            userService.deleteUser(email);
            ResponseEntity.noContent();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @PutMapping("/user/{email}/password")
    public ResponseEntity<HttpStatus> changePassword(@PathVariable String email,
                                                     @RequestBody UserPassword dto) {
        try {
            userService.changePassword(email, dto);
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

    @GetMapping("/user/{email}/reviews")
    public ResponseEntity<List<ReviewReturnDto>> getMyReviewList(@PathVariable String email,
                                                                 @RequestParam String sort) {
        try {
            List<ReviewReturnDto> reviews = userService.getMyReviewList(email, sort);
            return ResponseEntity.ok(reviews);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
