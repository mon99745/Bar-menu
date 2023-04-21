package danta.service.order;


import danta.model.Order;
import danta.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class MyOrderService {
    private final OrderRepository orderRepository;

//    public MyOrderSummaryDto getMyOrderSummary(Long ordererId, Pageable pageable) {
//        Page<OrderEntity> myOrders = myOrderDao.getMyOrders(ordererId, pageable);
//
//        List<MyOrderDto> contents = myOrders.stream()
//                .map(o -> MyOrderDto.builder()
//                        .orderId(o.getOrderId())
//                        .orderDate(o.getCreatedDate())
//                        .representativeImagePath(o.getOrderItemList().get(0).getItem().getImagePath())
//                        .representativeItemName(o.getOrderItemList().get(0).getItem().getName())
//                        .totalAmount(o.getTotalAmount())
//                        .orderStatus(o.getStatus().getStatus())
//                        .build())
//                .collect(Collectors.toList());
//        int total = contents.size();
//
//        return new MyOrderSummaryDto(contents, total);
//    }
//
//    public MyOrderDetailsDto getMyOrderDetails(Long orderId) {
//        OrderEntity orderEntity = myOrderDao.getMyOrderDetails(orderId)
//                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 주문번호입니다."));
//
//        List<MyOrderDetailsItemDto> myOrderDetailsItemDtoList = orderEntity.getOrderItemList().stream()
//                .map(oi -> {
//                    ItemEntity item = oi.getItem();
//                    return new MyOrderDetailsItemDto(item.getItemId(), item.getImagePath(), item.getName(), item.getPrice());
//                })
//                .collect(Collectors.toList());
//
//        MyOrderDetailsDto myOrderDetailsDto = MyOrderDetailsDto.builder()
//                .orderDate(orderEntity.getCreatedDate())
//                .orderId(orderId)
//                .receiverInfoDto(new MyOrderDetailsReceiverInfoDto(orderEntity.getOrderer().getUsername(), orderEntity.getOrderer().getEmail()))
//                .orderedItemList(myOrderDetailsItemDtoList)
//                .orderStatus(orderEntity.getStatus())
//                .build();

//        return myOrderDetailsDto;
//    }

    public void deleteMyOrder(Long ordererId, Long orderId) {
        Order order  = orderRepository.findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 주문입니다."));

        order.deleteOrder(ordererId);
    }
}
