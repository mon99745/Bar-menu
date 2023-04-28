package danta.controller.order;

import danta.converter.AuthenticationConverter;
import danta.domain.product.Product;
import danta.domain.order.Order;
import danta.domain.user.User;
import danta.domain.order.OrderRepository;
import danta.domain.product.ProductRepository;
import danta.domain.user.UserRepository;
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
    private final Order order;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;
    private final AuthenticationConverter authenticationConverter;

    /**
     * 주문 정보 설정
     * @param authentication
     * @param model
     */
    @ModelAttribute
    public void setOrderInfo(Authentication authentication, Model model) {

        User user = authenticationConverter.getUserFromAuthentication(authentication);
        model.addAttribute("shippingInfo", user.getUsername());

        OrdererDto orderer = createOrdererInfo(user);
        model.addAttribute("ordererInfo", orderer);
    }

    /**
     * 주문 페이지
     * 장바구니에 담긴 Product_id 들만을 받아온다.
     * @param authentication
     * @param orderRequest
     * @param model
     * @return
     */
    @PostMapping("/orders")
    public String getOrderPage(Authentication authentication,
                               @ModelAttribute OrderRequest orderRequest,
                               Model model) {
        User user = authenticationConverter.getUserFromAuthentication(authentication);

        List<Long> orderProductIdList = orderRequest.getOrderLineList()
                .stream()
                .map(ol -> ol.getProductId())
                .collect(Collectors.toList());
        OrderSummaryDto orderSummary = orderRepository.getOrderSummaryInCart(user.getId(), orderProductIdList);
        model.addAttribute("orderSummary", orderSummary);

        return "orders/order";
    }

    // 바로구매
    @PostMapping("/orders/direct")
    public String getOrderPageByDirect(@ModelAttribute OrderRequest orderRequest,
                                       Model model) {
        model.addAttribute("orderSummary", createOrderSummary(orderRequest));
        return "orders/order";
    }

    /**
     * 바로구매 요청의 OrderSummary를 생성
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

    /**
     * 주문자 정보 생성
     * @param orderer
     * @return
     */
    private OrdererDto createOrdererInfo(User orderer) {
        return new OrdererDto(orderer.getId(),
                orderer.getUsername());
//                orderer.getNickname());
    }

    /**
     * 주문 요청 처리
     */
    @PostMapping("/orders/order")
    public String order(Authentication authentication,
                        @ModelAttribute @Valid OrderRequest orderRequest) {
        User user = authenticationConverter.getUserFromAuthentication(authentication);

        Long orderId = orderService.order(user.getId(), orderRequest);

        return "redirect:/orders/complete/" + orderId;
    }

    /**
     * 주문완료 페이지 요청
     */
    @GetMapping("/orders/complete/{orderId}")
    public String getOrderCompletePage(@PathVariable("orderId") Long orderId,
                                       Model model) {
        model.addAttribute("orderId", orderId);
        model.addAttribute("orderDate", getOrderCompleteDate());
        return "orders/orderComplete";
    }
    /**
     * 주문 완료 일자
     */
    private String getOrderCompleteDate() {
        LocalDateTime now = LocalDateTime.now();
        return now.format(DateTimeFormatter.ISO_DATE);
    }

}
