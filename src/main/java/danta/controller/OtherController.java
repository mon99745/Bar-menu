package danta.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class OtherController {

    /**
     * 관리정보
     */
    @GetMapping("/management")
    public String management() {
        return "/admin/management";
    }

    /**
     * 채팅
     */
    @GetMapping("/auth/chat")
    public String chat() {
        return "/chat/chat";
    }

    /**
     * 룰렛
     */
    @GetMapping("/auth/roulette")
    public String roulette() {
        return "/roulette/roulette";
    }


}
