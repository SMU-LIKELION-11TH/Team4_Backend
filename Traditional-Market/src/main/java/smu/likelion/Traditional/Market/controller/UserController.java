package smu.likelion.Traditional.Market.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import smu.likelion.Traditional.Market.domain.enums.Code;
import smu.likelion.Traditional.Market.dto.common.ReturnDto;
import smu.likelion.Traditional.Market.dto.review.ReviewReturnDto;
import smu.likelion.Traditional.Market.dto.user.*;
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
    public ResponseEntity<ReturnDto> register(@RequestBody UserRegister dto) {
        try {
            return ResponseEntity.ok(ReturnDto.of(Code.OK, userService.createUser(dto)));
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
    public ResponseEntity<ReturnDto> getUser() {
        try {
            return ResponseEntity.ok(ReturnDto.of(Code.OK, userService.getUser()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @PutMapping(value = "/user", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<ReturnDto> updateUser(@RequestPart(value = "data", required = false) UserRequestDto dto,
                                                @RequestPart(value = "file", required = false) MultipartFile multipartFile) {
        try {
            return ResponseEntity.ok(ReturnDto.of(Code.OK, userService.updateUser(dto, multipartFile)));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @DeleteMapping("/user")
    public ResponseEntity<ReturnDto> deleteUser() {
        try {
            userService.deleteUser();
            return ResponseEntity.ok(ReturnDto.of(Code.OK));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @PutMapping("/user/password")
    public ResponseEntity<ReturnDto> changePassword(@RequestBody UserPassword dto) {
        try {
            userService.changePassword(dto);
            return ResponseEntity.ok(ReturnDto.of(Code.OK));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @GetMapping("/user/exist-email/{email}")
    public ResponseEntity<ReturnDto> existByEmail(@PathVariable String email) {
        try {
            return ResponseEntity.ok(ReturnDto.of(Code.OK, userService.existEmail(email)));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @GetMapping("/user/reviews")
    public ResponseEntity<ReturnDto> getMyReviewList(@RequestParam(required = false) String sort) {
        try {
            return ResponseEntity.ok(ReturnDto.of(Code.OK, userService.getMyReviewList(sort)));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
