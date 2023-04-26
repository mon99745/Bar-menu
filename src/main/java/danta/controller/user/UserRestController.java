package danta.controller.user;

import danta.model.User;
import danta.service.user.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Swagger 사용을 위해 모든 권한(authh) 을 허용한 상태
 */

@Slf4j
@Api(tags = UserRestController.TAG, description = "회원 로직을 관리하는 Rest API")
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
    public Long create(User user) {
        return userService.save(user);
    }

    /**
     * 회원조회 API
     */
    @Operation(summary = "2. 회원 조회")
    @GetMapping("read")
    public User read(@RequestBody Long userId) {
        return userService.findUser(userId);
    }

    /**
     * 회원수정 API
     */
    @Operation(summary = "3. 회원 정보 수정")
    @PutMapping("update")
    // @AuthenticationPrincipal에 PrincipalDetail타입으로 파라미터를 받으면 유저 정보를 얻을 수 있다.
    public Long update(@RequestBody User user) {
        return userService.update(user);
    }

    /**
     * 회원탈퇴 API
     */
    @Operation(summary = "4. 회원 탈퇴 ")
    @DeleteMapping("delete/{authId}")
    // id값을 주소에 받기 위해 @PathVariable
    public void delete(@RequestBody User user) {
        userService.delete(user);
    }

    /**
     * 전체 회원 조회 API (관리자용)
     */
    @Operation(summary = "5. 전체 회원 조회 (관리자용)")
    @GetMapping("readList")
    public List<User> list(){
        List<User> userList = userService.findAll();
        return userList;
    }
}
