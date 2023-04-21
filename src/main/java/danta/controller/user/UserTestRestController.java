package danta.controller.user;



import io.swagger.annotations.*;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotBlank;

@Slf4j
@Api(tags =UserTestRestController.TAG, description = "회원 로직을 테스트하는 관리하는 Rest API")
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
@RestController
@RequestMapping()
public class UserTestRestController {
    public static final String TAG = "User TEST API";

    @ApiOperation(value = "회원가입 요청문", notes = "회원가입 요청문을 생성하는 예제.")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "role", value = "권한", required = true, dataType = "string", defaultValue = "admin"),
            @ApiImplicitParam(name = "username", value = "이름", required = true, dataType = "string", defaultValue = "moon"),
            @ApiImplicitParam(name = "password", value = "비밀번호", required = true, dataType = "string", defaultValue = "12341234"),
            @ApiImplicitParam(name = "email", value = "이메일", required = true, dataType = "string", defaultValue = "example@gmail.com")
//            @ApiImplicitParam(name = "nickname", value = "이름", required = true, dataType = "string", defaultValue = "mjw")
    })
    @GetMapping(value = "/v1/UserSaveRequest/{role}/{username}/{password}/{email}")
    public void UserSaveRequest(
            @PathVariable @NotBlank @ApiParam(example = "admin") String role,
            @PathVariable @NotBlank @ApiParam(example = "moon") String username,
            @PathVariable @NotBlank @ApiParam(example = "12341234") String password,
            @PathVariable @NotBlank @ApiParam(example = "example@gmail.com") String email
//            @PathVariable @NotBlank @ApiParam(example = "mjw") String nickname
    ) {
//        //  회원가입 설정
//        User createMsg = new UserSaveRequestDto();
////        createMsg.setRole(createMsg);
//
//        createMsg.setRole(Role.valueOf(role));
//        createMsg.setUsername(username);
//        createMsg.setPassword(password);
//        createMsg.setEmail(email);
////        createMsg.nickname(nickname);
////        return createMsg.serialize();
    }
}
