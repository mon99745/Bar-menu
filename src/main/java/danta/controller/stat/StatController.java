package danta.controller.stat;

import danta.service.stat.StatService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;

@Controller
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class StatController {
    private StatService salesService;

    /**
     * 일간 매출액 화면 이동
     * @return
     */
    @RequestMapping(value = "salesDailyList", method = {RequestMethod.POST, RequestMethod.GET})
    public String getSalesDailyList(HttpServletRequest request, HttpServletResponse response, Model model){
        //TODO: 일간 매출액 화면 이동

        return "/sales/salesDailyList";
    }

    /**
     * 일별 기간 누적 매출현황 검색
     * @return
     */
    @GetMapping(value = "salesDailySearch")
    public @ResponseBody
    HashMap<String, Object> salesDailySearch(HttpServletRequest request) {
        HashMap<String, Object> result = new HashMap<>();
        //TODO: 일별 기간 누적 매출현황 검색

        return result;
    }
}
