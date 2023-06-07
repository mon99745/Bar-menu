package danta.controller.admin;

import danta.domain.admin.Admin;
import danta.domain.user.User;
import danta.service.admin.AdminService;
import danta.service.user.UserService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

@Controller
@RequestMapping(AdminController.PATH)
public class AdminController {
    public static final String PATH = "/auth/admin";

    @Autowired
    private AdminService adminService;
    @Autowired
    private UserService userService;

    /**
     * 관리정보
     */
    @ApiIgnore
    @GetMapping("/management")
    public String management() {
        return "/admin/management";
    }

    /**
     * 전체 회원 조회 API (관리자용)
     */
    @Operation(summary = "5. 전체 회원 조회 (관리자용)")
    @GetMapping("userList")
    public String userList(Model model){
        List<User> userList = userService.findAllUser();
        model.addAttribute("userList", userList);
        return "admin/user-list";
    }

    /**
     * 전체 관리자 조회 API
     */
    @Operation(summary = "6. 전체 관리자 조회")
    @GetMapping("adminList")
    public String adminList(Model model){
        List<Admin> adminList = adminService.findAllAdmin();
        model.addAttribute("adminList", adminList);
        return "admin/admin-list";
    }
}
