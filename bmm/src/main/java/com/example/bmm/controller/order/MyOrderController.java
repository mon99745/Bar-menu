package com.example.bmm.controller.order;

import com.example.bmm.converter.AuthenticationConverter;
import com.example.bmm.domain.guest.Guest;
import com.example.bmm.domain.guest.GuestRepository;
import com.example.bmm.domain.user.User;
import com.example.bmm.model.dto.order.MyOrderDetailDto;
import com.example.bmm.model.dto.order.MyOrderSummaryDto;
import com.example.bmm.service.guest.GuestService;
import com.example.bmm.service.order.MyOrderService;
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
        MyOrderSummaryDto myOrderSummaryDto;
        if(authentication == null) {
            // 게스트의 경우
            Guest guest = guestService.findGuestById(Long.valueOf(session.getId().hashCode()));
            myOrderSummaryDto = myOrderService.getMyOrderSummary(guest.getId(), pageable);
        } else{
            // 회원의 경우
            User user = authenticationConverter.getUserFromAuthentication(authentication);
            myOrderSummaryDto = myOrderService.getMyOrderSummary(user.getId(), pageable);
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

