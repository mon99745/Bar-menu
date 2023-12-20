package com.example.bmm.service.order;


import com.example.bmm.domain.guest.Guest;
import com.example.bmm.domain.order.Order;
import com.example.bmm.domain.order.OrderProduct;
import com.example.bmm.domain.order.OrderRepository;
import com.example.bmm.domain.product.Product;
import com.example.bmm.domain.product.ProductRepository;
import com.example.bmm.domain.user.User;
import com.example.bmm.model.dto.order.OrderRequest;
import com.example.bmm.service.cart.CartService;
import com.example.bmm.service.guest.GuestService;
import com.example.bmm.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final GuestService guestService;
    private final UserService userService;
    private final CartService cartService;
    private final ProductRepository productRepository;
    private final Product product;

    /**
     * GUEST 주문
     * @param ordererId
     * @param orderRequest
     * @param session
     * @return
     */
    public Long order(Long ordererId, OrderRequest orderRequest, HttpSession session) {
        // 엔티티 조회
        Guest orderer = guestService.findGuest(ordererId);

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
        Order order = Order.builder()
                .ordererId(orderer.getId())
                .orderProductList(orderProductList)
                .build();

        // 장바구니 비우기 (특정 상품들만 주문하는 경우가 존재하므로, 장바구니를 그냥 비우는게 아닌, id를 기준으로 비워야함)
        List<Long> productIdList = orderRequest.getOrderLineList().stream()
                .map(ol -> ol.getProductId())
                .collect(Collectors.toList());
        cartService.removeCartLines(orderer.getId(), productIdList);

        // 주문 저장
        return orderRepository.save(order).getOrderId();
    }

    /**
     * USER 주문
     * @param ordererId
     * @param orderRequest
     * @return
     */

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
        Order order = Order.builder()
                .ordererId(orderer.getId())
                .orderProductList(orderProductList)
                .build();

        // 장바구니 비우기 (특정 상품들만 주문하는 경우가 존재하므로, 장바구니를 그냥 비우는게 아닌, id를 기준으로 비워야함)
        List<Long> productIdList = orderRequest.getOrderLineList().stream()
                .map(ol -> ol.getProductId())
                .collect(Collectors.toList());
        cartService.removeCartLines(orderer.getId(), productIdList);

        // 주문 저장
        return orderRepository.save(order).getOrderId();
    }

    /**
     * 주문 취소
     * @param orderId
     */
    public void cancel(Long orderId) {
        Order order = orderRepository.findById(orderId).get();
        order.cancel();
    }

}

