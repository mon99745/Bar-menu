package com.example.bmm.service.stat;

import com.example.bmm.domain.order.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SalesStatService {
    private final OrderRepository orderRepository;
    @Autowired
    public SalesStatService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }
//
//    /**
//     * 매출액 불러오기
//     * @param startDate
//     * @param endDate
//     * @return
//     */
//    public Map<LocalDate, Double> calculateDailySalesStatistics(LocalDate startDate, LocalDate endDate) {
//        //특정 기간부터 특정 기간까지
//        LocalDateTime startDateTime = startDate.atStartOfDay();
//        LocalDateTime endDateTime = endDate.atTime(LocalTime.MAX);
//
////        List<Order> orders = orderRepository.findAllByDateBetween(startDateTime, endDateTime);
//        Map<LocalDate, Double> dailyStatistics = new HashMap<>();
//
//        for (Order order : orders) {
//            LocalDate orderDate = order.getRegDate().toLocalDate();
//            double totalAmount = order.getPrice();
//            dailyStatistics.put(orderDate, dailyStatistics.getOrDefault(orderDate, 0.0) + totalAmount);
//        }
//
//        return dailyStatistics;
//    }
}
