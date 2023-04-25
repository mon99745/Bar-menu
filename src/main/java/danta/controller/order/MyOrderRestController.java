package danta.controller.order;


import danta.service.order.MyOrderService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

@Slf4j
@Api(tags = MyOrderRestController.TAG,description = "자신의 주문을 관리하는 Rest API")
@ApiIgnore
@RestController
@RequiredArgsConstructor
@RequestMapping(MyOrderRestController.PATH)
public class MyOrderRestController {
    public static final String PATH = "/auth/api/v1";
    public static final String TAG = "My Order Rest API";
    private final MyOrderService myOrderService;

//    @GetMapping("/orders")
//    public MyOrderSummaryDto getMoreOrderList(Authentication authentication,
//                                              Pageable pageable) {
//        User user = authenticationConverter.getUserFromAuthentication(authentication);
//        MyOrderSummaryDto myOrderSummaryDto = myOrderService.getMyOrderSummary(user.getAuthId(), pageable);
//        return myOrderSummaryDto;
//    }
}

