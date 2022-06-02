package danta.controller.roulette;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RouletteController {

    /**
     * 룰렛
     */
    @GetMapping("/auth/roulette")
    public String roulette() {
        return "/roulette/roulette";
    }


}
