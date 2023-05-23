package danta.controller.order;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PaymentController {

    @GetMapping("/payment")
    public String payment(){
        //TODO: 게스트의 경우 Cart 초기화

        return "order/payment";
    }
}