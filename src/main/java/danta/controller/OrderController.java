package danta.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class OrderController {
    /**
     * 주문내역
     */
    @GetMapping("/auth/#/#")
    public String OrderList() {
        return "#";
    }
}
