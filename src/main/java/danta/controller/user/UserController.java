package danta.controller.user;

import danta.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {

    /**
     * 회원가입 페이지
     */
    @GetMapping("/auth/user/save")
    public String userSave() {
        return "user/user-save";
    }

    /**
     * 회원로그인 페이지
     */
    @GetMapping("/auth/user/login")
    public String userLogin() {
        return "user/user-login";
    }

    /**
     * 회원정보 페이지 (My Page)
     */
    @GetMapping("/user/info")
    public String userInfo() {
        return "user/user-info";
    }

    /**
     * 회원수정 페이지
     */
    @GetMapping("/user/update")
    public String userUpdate() {
        return "user/user-update";
    }

    /**
     * 회원탈퇴 페이지
     */
    @GetMapping("/user/delete")
    public String userDelete() {
        return "user/user-delete";
    }

    /**
     * 전체 회원목록 조회
     */
    @Autowired
    private UserService userService;

    @GetMapping("/admin/users")
    public String list(Model model) {
        model.addAttribute("users", userService.findAll());
        return "admin/user-list";
    }


}