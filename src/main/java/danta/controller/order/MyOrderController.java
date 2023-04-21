package danta.controller.order;

import danta.service.order.OrderService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Api(tags = MyOrderController.TAG, description = "자신의 주문을 관리하는 API")
@Controller
@RequiredArgsConstructor
@RequestMapping(MyOrderController.PATH)
public class MyOrderController {
    public static final String PATH = "/my/orders";
    public static final String TAG = "My Order API";
    private final OrderService orderService;

    @GetMapping("")
    public String getMyOrderListPage() {
        return "orders/myOrderList";
    }

    @GetMapping("{orderId}")
    public String getMyOrderDetails(@PathVariable("orderId") Long orderId,
                                    Model model) {
//        OrderDetail orderDetails = orderService.getMyOrderDetails(orderId);
//        model.addAttribute("myOrderDetails", myOrderDetails);
        return "orders/myOrderDetails";
    }

    @GetMapping("delete/{orderId}")
    public String deleteMyOrder(Authentication authentication,
                                @PathVariable("orderId") Long orderId) {
//        User user = authenticationConverter.getUserFromAuthentication(authentication);
//        orderService.deleteMyOrder(user.getAuthId(), orderId);
        return "redirect:/my/orders";
    }
}

