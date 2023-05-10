package danta.controller.order;

import danta.converter.AuthenticationConverter;
import danta.domain.guest.Guest;
import danta.domain.guest.GuestRepository;
import danta.domain.product.Product;
import danta.domain.user.User;
import danta.domain.order.OrderRepository;
import danta.domain.product.ProductRepository;
import danta.service.guest.GuestService;
import danta.service.order.OrderLineRequest;
import danta.service.order.OrderRequest;
import danta.service.order.OrderService;
import danta.model.dto.order.OrderProductDto;
import danta.model.dto.order.OrderSummaryDto;
import danta.model.dto.order.OrdererDto;
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
            Guest guest = guestService.findGuestById(Long.valueOf(session.getId().hashCode()));
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
     * 주문 페이지
     * 장바구니에 담긴 Product_id 들만을 받아온다.
     * TODO: 상품리스트가 NULL 로 찍히는 에러 발생
     * @param authentication
     * @param orderRequest
     * @param model
     * @return
     */

    @PostMapping("/order")
    public String getOrderPage(Authentication authentication,
                               @ModelAttribute OrderRequest orderRequest,
                               Model model, HttpSession session) {
        // TODO: 코드 중복 수정
        OrderSummaryDto orderSummaryDto;
        if(authentication == null) {
            // 게스트의 경우
            Guest guest = guestService.findGuestById(Long.valueOf(session.getId().hashCode()));
            List<Long> orderProductIdList = orderRequest.getOrderLineList()
                    .stream()
                    .map(ol -> ol.getProductId())
                    .collect(Collectors.toList());

            orderSummaryDto = orderRepository.getOrderSummaryInCart(guest.getId(), orderProductIdList);
        } else{
            // 회원의 경우
            User user = authenticationConverter.getUserFromAuthentication(authentication);
            List<Long> orderProductIdList = orderRequest.getOrderLineList()
                    .stream()
                    .map(ol -> ol.getProductId())
                    .collect(Collectors.toList());

            orderSummaryDto = orderRepository.getOrderSummaryInCart(user.getId(), orderProductIdList);
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

        return new OrderSummaryDto((List<OrderProductDto>) orderProductDto);
    }

    // TODO:주문자 정보 생성 메소드 통합
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
        return new OrdererDto(orderer.getId(), null);
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
            orderId = orderService.order(guest.getId(), orderRequest);

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
