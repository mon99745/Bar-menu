package danta.service.order;


import danta.model.order.Order;
import danta.repository.OrderRepository;
import danta.service.order.dto.MyOrderDetail;
import danta.service.order.dto.MyOrderDetailProduct;
import danta.service.order.dto.MyOrderDetailReceiver;
import danta.service.order.dto.MyOrderSummaryDto;
import lombok.RequiredArgsConstructor;
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
        Page<OrderEntity> myOrders = myOrderDao.getMyOrders(ordererId, pageable);

        List<MyOrderDto> contents = myOrders.stream()
                .map(o -> MyOrderDto.builder()
                        .orderId(o.getOrderId())
                        .orderDate(o.getCreatedDate())
                        .representativeImagePath(o.getOrderItemList().get(0).getItem().getImagePath())
                        .representativeItemName(o.getOrderItemList().get(0).getItem().getName())
                        .totalAmount(o.getTotalAmount())
                        .orderStatus(o.getStatus().getStatus())
                        .build())
                .collect(Collectors.toList());
        int total = contents.size();

        return new MyOrderSummaryDto(contents, total);
    }

    public MyOrderDetailsDto getMyOrderDetails(Long orderId) {
        OrderEntity orderEntity = myOrderDao.getMyOrderDetails(orderId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 주문번호입니다."));

        List<MyOrderDetailProduct> myOrderDetailsItemDtoList = orderEntity.getOrderItemList().stream()
                .map(oi -> {
                    ItemEntity item = oi.getItem();
                    return new MyOrderDetailProduct(item.getItemId(), item.getImagePath(), item.getName(), item.getPrice());
                })
                .collect(Collectors.toList());

        MyOrderDetail myOrderDetailsDto = MyOrderDetail.builder()
                .orderDate(orderEntity.getCreatedDate())
                .orderId(orderId)
                .receiverInfoDto(new MyOrderDetailReceiver(orderEntity.getOrderer().getUsername(), orderEntity.getOrderer().getEmail()))
                .orderedItemList(myOrderDetailsItemDtoList)
                .orderStatus(orderEntity.getStatus())
                .build();

        return myOrderDetailsDto;
    }

    public void deleteMyOrder(Long ordererId, Long orderId) {
        Order order  = orderRepository.findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 주문입니다."));

        order.deleteOrder(ordererId);
    }
}
