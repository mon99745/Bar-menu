package danta.controller.order;

import danta.domain.user.AuthenticationConverter;
import danta.domain.user.User;
import danta.model.dto.order.MyOrderSummaryDto;
import danta.service.order.MyOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MyOrderRestController {
    private final AuthenticationConverter authenticationConverter;
    private final MyOrderService myOrderService;

    @GetMapping("/api/v1/orders")
    public MyOrderSummaryDto getMoreOrderList(Authentication authentication,
                                              Pageable pageable) {
        User user = authenticationConverter.getUserFromAuthentication(authentication);
        MyOrderSummaryDto myOrderSummaryDto = myOrderService.getMyOrderSummary(user.getAuthId(), pageable);
        return myOrderSummaryDto;
    }
}

