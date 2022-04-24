package danta.controller.order;

import danta.domain.user.AuthenticationConverter;
import danta.domain.user.User;
import danta.model.dto.order.MyOrderDetailsDto;
import danta.service.order.MyOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
@Controller
@RequiredArgsConstructor
public class MyOrderController {
    private final MyOrderService myOrderService;
    private final AuthenticationConverter authenticationConverter;

    @GetMapping("/my/orders")
    public String getMyOrderListPage() {
        return "orders/myOrderList";
    }

    @GetMapping("/my/orders/{orderId}")
    public String getMyOrderDetails(@PathVariable("orderId") Long orderId,
                                    Model model) {
        MyOrderDetailsDto myOrderDetails = myOrderService.getMyOrderDetails(orderId);
        model.addAttribute("myOrderDetails", myOrderDetails);
        return "orders/myOrderDetails";
    }

    @GetMapping("/my/orders/delete/{orderId}")
    public String deleteMyOrder(Authentication authentication,
                                @PathVariable("orderId") Long orderId) {
        User user = authenticationConverter.getUserFromAuthentication(authentication);
        myOrderService.deleteMyOrder(user.getAuthId(), orderId);
        return "redirect:/my/orders";
    }
}
