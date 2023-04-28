package danta.service.order;


import danta.domain.product.Product;
import danta.domain.order.Order;
import danta.domain.order.OrderRepository;
import danta.model.dto.order.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class MyOrderService {
    private final OrderRepository orderRepository;

    public MyOrderSummaryDto getMyOrderSummary(Long ordererId, Pageable pageable) {
        Page<Order> myOrders = orderRepository.getMyOrders(ordererId, pageable);

        List<MyOrderDto> contents = myOrders.stream()
                .map(o -> MyOrderDto.builder()
                        .orderId(o.getOrderId())
                        .orderDate(o.getRegDate())
                        .representativeImagePath(o.getOrderProductList().get(0).getProduct().getImage())
                        .representativeProductName(o.getOrderProductList().get(0).getProduct().getName())
                        .totalAmount(o.getTotalAmount())
                        .orderStatus(o.getStatus().getStatus())
                        .build())
                .collect(Collectors.toList());
        int total = contents.size();

        return new MyOrderSummaryDto(contents, total);
    }

    public MyOrderDetailDto getMyOrderDetails(Long orderId) {
        Order order = orderRepository.getMyOrderDetails(orderId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 주문번호입니다."));

        List<MyOrderDetailProductDto> myOrderDetailsProductDtoList = order.getOrderProductList().stream()
                .map(oi -> {
                    Product product = oi.getProduct();
                    return new MyOrderDetailProductDto(product.getId(), product.getImage(), product.getName(), product.getPrice());
                })
                .collect(Collectors.toList());

        MyOrderDetailDto myOrderDetailsDto = MyOrderDetailDto.builder()
                .orderDate(order.getRegDate())
                .orderId(orderId)
                .receiverInfoDto(new MyOrderDetailReceiverDto(order.getOrderer().getUsername(), order.getOrderer().getUsername()))
                .orderedProductList(myOrderDetailsProductDtoList)
                .orderStatus(order.getStatus())
                .build();

        return myOrderDetailsDto;
    }

    public void deleteMyOrder(Long ordererId, Long orderId) {
        Order order  = orderRepository.findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 주문입니다."));

        order.deleteOrder(ordererId);
    }
}
