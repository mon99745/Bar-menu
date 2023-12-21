package com.example.bmm.controller.user;

import com.example.bmm.domain.user.User;
import com.example.bmm.service.user.UserService;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

/**
 * Swagger 사용을 위해 모든 권한(auth) 을 허용한 상태
 */

@Slf4j
@Api(tags = UserRestController.TAG)
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
@RestController
@RequestMapping(UserRestController.PATH)
public class UserRestController {

    @Value("${security.auth}")
    private boolean auth;
    public static final String PATH = "/auth/api/v1";
    public static final String TAG = "User Rest API";
    private final UserService userService;

    /**
     * 회원가입 API
     */
    @Operation(summary = "1. 회원 가입")
    @PostMapping("create")
    public User create(@RequestBody User createMsg) {
        userService.save(createMsg);
        return createMsg;
    }

    /**
     * 회원조회 API
     */
    @Operation(summary = "2. 회원 조회")
    @GetMapping("read/{userId}")
    public User read(@PathVariable Long userId) {
        return userService.findUser(userId);
    }

    /**
     * 회원수정 API
     */
    @Operation(summary = "3. 회원 정보 수정")
    @PostMapping("update/{userId}")
    public User update(@PathVariable Long userId, @RequestBody User updateMsg) {
        userService.update(userId, updateMsg);
        return updateMsg;
    }

    /**
     * 회원탈퇴 API
     */
    @Operation(summary = "4. 회원 탈퇴 ")
    @PostMapping("delete/{userId}")
    public void delete(@PathVariable Long userId, @RequestBody User deleteMsg) {
        userService.delete(userId, deleteMsg);
    }
}
