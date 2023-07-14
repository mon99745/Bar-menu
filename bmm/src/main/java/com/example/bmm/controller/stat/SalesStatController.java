package com.example.bmm.controller.stat;

import com.example.bmm.service.stat.SalesStatService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class SalesStatController {
    private SalesStatService salesStatService;

    @Autowired
    public SalesStatController(SalesStatService salesService){
        this.salesStatService = salesService;
    }

//    @GetMapping("/statistics")
//    public String showStatistics(@RequestParam int year, @RequestParam int month, @RequestParam int day, Model model) {
//        LocalDate startDate = LocalDate.of(year, month, day);
//        LocalDate endDate = startDate.plusDays(1);
//
//        Map<LocalDate, Double> dailyStatistics = salesStatService.calculateDailySalesStatistics(startDate, endDate);
//        // 결과를 모델에 추가하여 템플릿에서 사용할 수 있도록 함
//        model.addAttribute("dailyStatistics", dailyStatistics);
//
//        return "statistics";
//    }

//    /**
//     * TODO:일간 매출액 화면 이동
//     * @return
//     */
//    @RequestMapping(value = "salesDailyList", method = {RequestMethod.POST, RequestMethod.GET})
//    public String getSalesDailyList(HttpServletRequest request, HttpServletResponse response, Model model){
//
//
//        return "/sales/salesDailyList";
//    }
//
//    /**
//     * TODO: 일별 기간 누적 매출현황 검색
//     * @return
//     */
//    @GetMapping(value = "salesDailySearch")
//    public @ResponseBody
//    HashMap<String, Object> salesDailySearch(HttpServletRequest request) {
//        HashMap<String, Object> result = new HashMap<>();
//
//
//        return result;
//    }

}
