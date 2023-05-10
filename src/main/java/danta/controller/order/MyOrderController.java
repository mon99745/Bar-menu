package danta.controller.order;

import danta.converter.AuthenticationConverter;
import danta.domain.guest.Guest;
import danta.domain.guest.GuestRepository;
import danta.domain.order.MyOrderRepository;
import danta.domain.order.OrderDetail;
import danta.domain.user.User;
import danta.model.dto.cart.CartLineDto;
import danta.model.dto.order.MyOrderDetailDto;
import danta.model.dto.order.MyOrderSummaryDto;
import danta.service.guest.GuestService;
import danta.service.order.MyOrderService;
import danta.service.order.OrderService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpSession;
import java.util.List;

@Slf4j
@Api(tags = MyOrderController.TAG, description = "자신의 주문을 관리하는 API")
@ApiIgnore
@Controller
@RequiredArgsConstructor
@RequestMapping(MyOrderController.PATH)
public class MyOrderController {
    public static final String PATH = "/my/order";
    public static final String TAG = "My Order API";
    private final MyOrderService myOrderService;
    private final GuestService guestService;
    private final GuestRepository guestRepository;
    private final AuthenticationConverter authenticationConverter;


    @GetMapping("")
    public String getMyOrderListPage(Authentication authentication, Model model, Pageable pageable, HttpSession session) {
        //TODO : 주문 페이지 조회 에러
        MyOrderSummaryDto myOrderSummaryDto = null;
        if(authentication == null) {
            // 게스트의 경우
            Guest guest = guestService.findGuestById(Long.valueOf(session.getId().hashCode()));
            myOrderService.getMyOrderSummary(guest.getId(), pageable);
        } else{
            // 회원의 경우
            User user = authenticationConverter.getUserFromAuthentication(authentication);
            myOrderService.getMyOrderSummary(user.getId(), pageable);
        }
        model.addAttribute("myOrderSummary", myOrderSummaryDto);
        return "order/myOrderList";
    }

    @GetMapping("{orderId}")
    public String getMyOrderDetails(@PathVariable("orderId") Long orderId,
                                    Model model) {
        MyOrderDetailDto myOrderDetail = myOrderService.getMyOrderDetails(orderId);
        model.addAttribute("myOrderDetails", myOrderDetail);
        return "order/myOrderDetail";
    }

    @GetMapping("delete/{orderId}")
    public String deleteMyOrder(Authentication authentication,
                                @PathVariable("orderId") Long orderId, HttpSession session) {
        if(authentication == null) {
            // 게스트의 경우
            Guest guest = guestService.findGuestById(Long.valueOf(session.getId().hashCode()));
            myOrderService.deleteMyOrder(guest.getId(), orderId);
        } else{
         // 회원의 경우
            User user = authenticationConverter.getUserFromAuthentication(authentication);
            myOrderService.deleteMyOrder(user.getId(), orderId);
        }
        return "redirect:/my/order";
    }
}

