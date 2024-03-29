package com.example.bmm.controller.order;

import com.example.bmm.converter.AuthenticationConverter;
import com.example.bmm.domain.guest.Guest;
import com.example.bmm.domain.order.OrderRepository;
import com.example.bmm.domain.product.Product;
import com.example.bmm.domain.product.ProductRepository;
import com.example.bmm.domain.user.User;
import com.example.bmm.model.dto.order.OrderLineRequest;
import com.example.bmm.model.dto.order.OrderProductDto;
import com.example.bmm.model.dto.order.OrderRequest;
import com.example.bmm.model.dto.order.OrderSummaryDto;
import com.example.bmm.model.dto.order.OrdererDto;
import com.example.bmm.service.guest.GuestService;
import com.example.bmm.service.order.OrderService;
import io.swagger.annotations.Api;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
@Api(tags = OrderController.TAG, description = "주문을 관리하는 API")
@ApiIgnore
@Controller
public class OrderController {
    public static final String TAG = "Order API";
    private final OrderService orderService;
    private final GuestService guestService;
    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;

    private final AuthenticationConverter authenticationConverter;

    /**
     * 주문 정보 설정
     * @param authentication
     * @param model
     */
    @ModelAttribute
    public void setOrderInfo(Authentication authentication, Model model, HttpSession session) {
        OrdererDto orderer;
        if(authentication == null) {
            // 게스트의 경우
            Guest guest =  guestService.createGuest(session);
            model.addAttribute("shippingInfo", "GUEST");
            orderer = createOrdererInfo(guest);
            model.addAttribute("ordererInfo", orderer);

        } else {
            // 회원의 경우
            User user = authenticationConverter.getUserFromAuthentication(authentication);
            model.addAttribute("shippingInfo", user.getUsername());
            orderer = createOrdererInfo(user);
            model.addAttribute("ordererInfo", orderer);
        }
    }

    /**
     * 주문
     * 장바구니에 담긴 Product_id 들만을 받아온다.
     * @param authentication
     * @param orderRequest
     * @param model
     * @return
     */

    @PostMapping("/order")
    public String getOrderPage(Authentication authentication,
                               @ModelAttribute OrderRequest orderRequest,
                               Model model, HttpSession session) {
        OrderSummaryDto orderSummaryDto;
        if(authentication == null) {
            // 게스트의 경우
            Guest guest = guestService.findGuestById(Long.valueOf(session.getId().hashCode()));
            List<Long> orderProductIdList = orderRequest.getOrderLineList()
                    .stream()
                    .map(ol -> ol.getProductId())
                    .collect(Collectors.toList());

            orderSummaryDto = orderRepository.getOrderSummaryInCart(null, guest.getId(), orderProductIdList);
        } else{
            // 회원의 경우
            User user = authenticationConverter.getUserFromAuthentication(authentication);
            List<Long> orderProductIdList = orderRequest.getOrderLineList()
                    .stream()
                    .map(ol -> ol.getProductId())
                    .collect(Collectors.toList());

            orderSummaryDto = orderRepository.getOrderSummaryInCart(authentication, user.getId(), orderProductIdList);
        }

        model.addAttribute("orderSummary", orderSummaryDto);

        return "order/order";
    }

    /**
     * 즉시 구매
     * @param orderRequest
     * @param model
     * @return
     */
    @PostMapping("/order/direct")
    public String getOrderPageByDirect(@ModelAttribute OrderRequest orderRequest,
                                       Model model) {
        model.addAttribute("orderSummary", createOrderSummary(orderRequest));
        return "order/order";
    }

    /**
     * 즉시 구매 요청 시 OrderSummary를 생성
     */
    private OrderSummaryDto createOrderSummary(OrderRequest orderRequest) {
        // 바로구매시 하나의 아이템만을 구매하게 되므로 첫번째 인덱스의 아이템을 이용
        OrderLineRequest orderLineRequest = orderRequest.getOrderLineList().get(0);

        Product product = productRepository.findById(orderLineRequest.getProductId())
                .get();
        OrderProductDto orderProductDto = new OrderProductDto(product.getId(),
                product.getName(),
                product.getPrice(),
                orderLineRequest.getOrderCount());

        return new OrderSummaryDto(Arrays.asList(orderProductDto));
    }

    /**
     * 주문자 정보 생성
     * USER
     * @param orderer
     * @return
     */
    private OrdererDto createOrdererInfo(User orderer) {
        return new OrdererDto(orderer.getId(),
                orderer.getUsername());
    }

    /**
     * 주문자 정보 생성
     * GUEST
     * @param orderer
     * @return
     */
    private OrdererDto createOrdererInfo(Guest orderer) {
        return new OrdererDto(orderer.getId(), "GUEST");
    }

    /**
     * 주문
     */
    @PostMapping("/order/order")
    public String order(Authentication authentication,
                        @ModelAttribute @Valid OrderRequest orderRequest, HttpSession session) {
        Long orderId;
        if(authentication == null) {
            // 게스트의 경우
            Guest guest = guestService.findGuestById(Long.valueOf(session.getId().hashCode()));
            orderId = orderService.order(guest.getId(), orderRequest, session);

        } else {
         // 회원의 경우
            User user = authenticationConverter.getUserFromAuthentication(authentication);
            orderId = orderService.order(user.getId(), orderRequest);
        }

        return "redirect:/order/complete/" + orderId;
    }

    /**
     * 주문완료 페이지
     */
    @GetMapping("/order/complete/{orderId}")
    public String getOrderCompletePage(@PathVariable("orderId") Long orderId,
                                       Model model) {
        model.addAttribute("orderId", orderId);
        model.addAttribute("orderDate", getOrderCompleteDate());
        return "order/orderComplete";
    }
    /**
     * 주문 완료 일자
     */
    private String getOrderCompleteDate() {
        LocalDateTime now = LocalDateTime.now();
        return now.format(DateTimeFormatter.ISO_DATE);
    }

}
