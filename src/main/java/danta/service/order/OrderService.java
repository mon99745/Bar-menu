package danta.service.order;


import danta.domain.order.Order;
import danta.domain.order.OrderProduct;
import danta.domain.product.Product;
import danta.domain.user.User;
import danta.domain.order.OrderRepository;
import danta.domain.product.ProductRepository;
import danta.service.cart.CartService;
import danta.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final UserService userService;
    private final CartService cartService;
    private final ProductRepository productRepository;
    private final Product product;

    public Long order(Long ordererId, OrderRequest orderRequest) {
        // 엔티티 조회
        User orderer = userService.findUser(ordererId);


        // 주문상품 생성
        List<OrderProduct> orderProductList = orderRequest.getOrderLineList()
                .stream()
                .map(ol -> {
                    Product product = productRepository.findById(ol.getProductId())
                            .get();
                    return new OrderProduct(product, ol.getOrderCount());
                })
                .collect(Collectors.toList());

        // 주문 상품 재고 줄이기
        orderProductList.stream()
                .forEach(oi -> oi.removeStockQuantity());

        // 주문 생성
        Order orderEntity = Order.builder()
                .orderer(orderer)
                .orderProductList(orderProductList)
                .build();

        // 장바구니 비우기 (특정 상품들만 주문하는 경우가 존재하므로, 장바구니를 그냥 비우는게 아닌, id를 기준으로 비워야함)
        List<Long> productIdList = orderRequest.getOrderLineList().stream()
                .map(ol -> ol.getProductId())
                .collect(Collectors.toList());
        cartService.removeCartLines(orderer.getId(), productIdList);

        // 주문 저장
        return orderRepository.save(orderEntity).getOrderId();
    }

    public void cancel(Long orderId) {
        Order order = orderRepository.findById(orderId).get();
        order.cancel();
    }
}

