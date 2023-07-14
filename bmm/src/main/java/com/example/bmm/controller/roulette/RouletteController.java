package com.example.bmm.controller.roulette;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import springfox.documentation.annotations.ApiIgnore;

@Controller
public class RouletteController {

    /**
     * 룰렛
     */
    @ApiIgnore
    @GetMapping("/auth/roulette")
    public String roulette() {
        return "/roulette/roulette";
    }


}
