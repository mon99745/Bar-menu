package danta.controller.chat;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ChatController {
    /**
     * 채팅
     */
    @GetMapping("/chat")
    public String chat() {
        return "/chat/chat";
    }

}
