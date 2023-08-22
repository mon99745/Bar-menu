package com.example.bma.cotroller.admin;

import com.example.bma.service.admin.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import springfox.documentation.annotations.ApiIgnore;

@Controller
@RequestMapping(AdminController.PATH)
public class AdminController {
    public static final String PATH = "/auth/admin";

    @Autowired
    private AdminService adminService;

    /**
     * 관리정보
     */
    @ApiIgnore
    @GetMapping("/management")
    public String management() {
        return "/admin/management";
    }


}
