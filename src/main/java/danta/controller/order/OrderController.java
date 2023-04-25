package danta.controller.order;

import danta.model.Order;
import danta.model.User;
import danta.repository.ProductRepository;
import danta.service.order.OrderRequest;
import danta.service.order.OrderService;
import io.swagger.annotations.Api;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationConverter;
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
    private final ProductRepository productRepository;

//    @ModelAttribute
//    public void setOrderInfo(Authentication authentication,
//                             Model model) {
//        User user = authenticationConverter.getUserFromAuthentication(authentication);
//        model.addAttribute("shippingInfo", user.getId());
//
////        order ordererInfo = createOrdererInfo(user);
////        model.addAttribute("ordererInfo", ordererInfo);
//    }

//    // 주문 페이지
//    // 장바구니에 담긴 item id 들만을 받아옴
//    @PostMapping("/orders")
//    public String getOrderPage(Authentication authentication,
//                               @ModelAttribute OrderRequest orderRequest,
//                               Model model) {
//        User user = authenticationConverter.getUserFromAuthentication(authentication);
//
//        List<Long> orderItemIdList = orderRequest.getOrderLineList()
//                .stream()
//                .map(ol -> ol.getItemId())
//                .collect(Collectors.toList());
////        OrderSummaryDto orderSummaryDto = orderDao.getOrderSummaryInCart(user.getAuthId(), orderItemIdList);
////        model.addAttribute("orderSummary", orderSummaryDto);
//
//        return "orders/order";
//    }

    // 바로구매
    @PostMapping("/orders/direct")
    public String getOrderPageByDirect(@ModelAttribute OrderRequest orderRequest,
                                       Model model) {
//        model.addAttribute("orderSummary", createOrderSummary(orderRequest));
        return "orders/order";
    }

    // 바로구매 요청의 OrderSummary를 생성
//    private OrderSummaryDto createOrderSummary(OrderRequest orderRequest) {
//        // 바로구매시 하나의 아이템만을 구매하게 되므로 첫번째 인덱스의 아이템을 이용
//        OrderLineRequest orderLineRequest = orderRequest.getOrderLineList().get(0);
//
//        ItemEntity itemEntity = itemRepository.findById(orderLineRequest.getItemId())
//                .get();
//        OrderItemDto orderItemDto = new OrderItemDto(itemEntity.getItemId(),
//                itemEntity.getName(),
//                itemEntity.getPrice(),
//                orderLineRequest.getOrderCount());
//
//        return new OrderSummaryDto(Arrays.asList(orderItemDto));
//    }


//    private OrdererInfoDto createOrdererInfo(User orderer) {
//        return new OrdererInfoDto(orderer.getAuthId(),
//                orderer.getUsername());
////                orderer.getNickname());
//    }

    // 주문 요청 처리
//    @PostMapping("/orders/order")
//    public String order(Authentication authentication,
//                        @ModelAttribute @Valid OrderRequest orderRequest) {
//        User user = authenticationConverter.getUserFromAuthentication(authentication);

//        Long orderId = orderService.order(user.getId(), orderRequest);

//        return "redirect:/orders/complete/" + orderId;
//    }

    // 주문완료 페이지 요청
    @GetMapping("/orders/complete/{orderId}")
    public String getOrderCompletePage(@PathVariable("orderId") Long orderId,
                                       Model model) {
        model.addAttribute("orderId", orderId);
        model.addAttribute("orderDate", getOrderCompleteDate());
        return "orders/orderComplete";
    }

    private String getOrderCompleteDate() {
        LocalDateTime now = LocalDateTime.now();
        return now.format(DateTimeFormatter.ISO_DATE);
    }

}
