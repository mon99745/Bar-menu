package danta.controller.user;

import danta.domain.user.User;
import danta.service.user.UserService;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

/**
 * Swagger 사용을 위해 모든 권한(authh) 을 허용한 상태
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
    public Long create(@RequestBody User user) {
        return userService.save(user);
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
    @PutMapping("update/{userId}")
    public User update(@PathVariable Long userId, @RequestBody User user) {
        return userService.update(userId, user);
    }

    /**
     * 회원탈퇴 API
     */
    @Operation(summary = "4. 회원 탈퇴 ")
    @DeleteMapping("delete/{userId}")
    public void delete(@PathVariable Long userId, @RequestBody User user) {
        userService.delete(userId, user);
    }
}
