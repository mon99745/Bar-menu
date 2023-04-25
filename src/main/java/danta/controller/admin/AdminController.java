package danta.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import springfox.documentation.annotations.ApiIgnore;

@Controller
public class AdminController {
    /**
     * 관리정보
     */
    @ApiIgnore
    @GetMapping("/management")
    public String management() {
        return "/admin/management";
    }

}
