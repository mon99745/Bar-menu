package danta.controller.chat;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import springfox.documentation.annotations.ApiIgnore;

@Controller
public class ChatController {
    /**
     * 채팅
     */
    @ApiIgnore
    @GetMapping("/chat")
    public String chat() {
        return "/chat/chat";
    }

}
