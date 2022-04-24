package danta.service.order;

import danta.domain.item.ItemEntity;
import danta.domain.item.ItemRepository;
import danta.domain.order.OrderEntity;
import danta.domain.order.OrderItemEntity;
import danta.domain.order.OrderRepository;
import danta.domain.user.User;
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
    private final ItemRepository itemRepository;

    public Long order(Long ordererId, OrderRequest orderRequest) {
        // 엔티티 조회
        User orderer = userService.findUser(ordererId);

        // 배송지 생성
        DeliveryEntity deliveryEntity = DeliveryEntity.builder()
                .address(orderer.getAddress())
                .build();

        // 주문상품 생성
        List<OrderItemEntity> orderItemEntityList = orderRequest.getOrderLineList()
                .stream()
                .map(ol -> {
                    ItemEntity itemEntity = itemRepository.findById(ol.getItemId())
                            .get();
                    return new OrderItemEntity(itemEntity, ol.getOrderCount());
                })
                .collect(Collectors.toList());

        // 주문 상품 재고 줄이기
        orderItemEntityList.stream()
                .forEach(oi -> oi.removeStockQuantity());

        // 주문 생성
        OrderEntity orderEntity = OrderEntity.builder()
                .orderer(orderer)
                .deliveryInformation(deliveryEntity)
                .orderItemEntityList(orderItemEntityList)
                .build();

        // 장바구니 비우기 (특정 상품들만 주문하는 경우가 존재하므로, 장바구니를 그냥 비우는게 아닌, id를 기준으로 비워야함)
        List<Long> itemIdList = orderRequest.getOrderLineList().stream()
                .map(ol -> ol.getItemId())
                .collect(Collectors.toList());
        cartService.removeCartLines(orderer.getAuthId(), itemIdList);

        // 주문 저장
        return orderRepository.save(orderEntity).getOrderId();
    }

    public void cancel(Long orderId) {
        OrderEntity order = orderRepository.findById(orderId).get();
        order.cancel();
    }


}

