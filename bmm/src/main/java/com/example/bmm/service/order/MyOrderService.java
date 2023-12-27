package com.example.bmm.service.order;


import com.example.bmc.exception.BmmError;
import com.example.bmc.exception.BmcException;
import com.example.bmm.model.dto.order.MyOrderDetailDto;
import com.example.bmm.model.dto.order.MyOrderDetailProductDto;
import com.example.bmm.model.dto.order.MyOrderDetailReceiverDto;
import com.example.bmm.model.dto.order.MyOrderDto;
import com.example.bmm.model.dto.order.MyOrderSummaryDto;
import com.example.bmm.domain.order.MyOrderRepository;
import com.example.bmm.domain.order.Order;
import com.example.bmm.domain.product.Product;
import com.example.bmm.service.user.UserService;
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
    private final UserService userService;
    private final MyOrderRepository MyOrderRepository;

    public MyOrderSummaryDto getMyOrderSummary(Long ordererId, Pageable pageable) {
        Page<Order> myOrders = MyOrderRepository.getMyOrders(ordererId, pageable);

        List<MyOrderDto> contents = myOrders.stream()
                .map(o -> MyOrderDto.builder()
                        .orderId(o.getOrderId())
                        .orderDate(o.getRegDate())
                        .representativeImage(o.getOrderProductList().get(0).getProduct().getImage())
                        .representativeProductName(o.getOrderProductList().get(0).getProduct().getName())
                        .totalAmount(o.getTotalAmount())
                        .orderStatus(o.getStatus())
                        .build())
                .collect(Collectors.toList());
        int total = contents.size();

        return new MyOrderSummaryDto(contents, total);
    }

    public MyOrderDetailDto getMyOrderDetails(Long orderId) {
        Order order = MyOrderRepository.getMyOrderDetails(orderId)
                .orElseThrow(() -> new BmcException(BmmError.BMM_ORDER_ID_NOT_EXIST, null));

        List<MyOrderDetailProductDto> myOrderDetailsProductDtoList = order.getOrderProductList().stream()
                .map(oi -> {
                    Product product = oi.getProduct();
                    return new MyOrderDetailProductDto(product.getId(), product.getImage(), product.getName(), product.getPrice());
                })
                .collect(Collectors.toList());

        MyOrderDetailDto myOrderDetailsDto = MyOrderDetailDto.builder()
                .orderDate(order.getRegDate())
                .orderId(orderId)
                .receiverInfoDto(new MyOrderDetailReceiverDto(userService.findUser(order.getOrdererId()).getUsername()))
                .orderedProductList(myOrderDetailsProductDtoList)
                .orderStatus(order.getStatus())
                .build();

        return myOrderDetailsDto;
    }

    public void deleteMyOrder(Long ordererId, Long orderId) {
        Order order  = MyOrderRepository.findById(orderId)
                .orElseThrow(() -> new BmcException(BmmError.BMM_ORDER_ID_NOT_EXIST, null));
        order.deleteOrder(ordererId);
    }
}
