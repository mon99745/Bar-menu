package danta.controller.user;

import danta.model.User;
import danta.service.user.UserService;
import io.swagger.annotations.Api;
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

//    if(auth){
//        String PathTemp = "/auth/api/v1";
//    }else{
//        String PathTemp = "/api/v1";
//    }

    public static final String PATH = "/auth/api/v1";
    public static final String TAG = "User Rest API";
    private final UserService userService;

//    v1 은 현재 사용 중인 디플로이 연동 API의 버전을 의미.
//    추후 기능 추가 및 변경으로 인하여 API가 바뀌면 v2 등을 사용하게 될 수도 있다.
//    JSON 데이터 변환하는 형식은 생략

    /**
     * 회원가입 API
     */
    @PostMapping("create")
    public String create(@RequestBody User user) {
        return userService.save(user);
    }

    /**
     * 전체 회원 조회 API (관리자용)
     */
    @GetMapping("list")
    public List<User> list(){
        List<User> userList = userService.findAll();
//        model.addAttribute("users", users);
        return userList;
    }

    /**
     * 회원수정 API
     */
    @PutMapping("update")
    // @AuthenticationPrincipal에 PrincipalDetail타입으로 파라미터를 받으면 유저 정보를 얻을 수 있다.
    public String update(@RequestBody User user) {
        return userService.update(user);
    }

    /**
     * 회원탈퇴 API
     */
    @DeleteMapping("delete/{authId}")
    // id값을 주소에 받기 위해 @PathVariable
    public void delete(@RequestBody User user) {
        userService.delete(user);
    }
}
