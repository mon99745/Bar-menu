package danta.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {
    /**
     * 관리정보
     */
    @GetMapping("/management")
    public String management() {
        return "/admin/management";
    }

}
