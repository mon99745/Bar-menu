package danta.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

@RequiredArgsConstructor
@Controller
public class IndexController {

//    @GetMapping("/")
//// @PageableDefault를 설정하면 페이지의 size, 정렬순을 정할 수 있다.
//// 한 페이지당 5 Size, 최신글을 제일 맨위로 볼 수 있게 해둠.
//// 메뉴판에 메뉴가 보이게 하기위해서는 CatalogController와 합쳐야됨
//    public String index(Model model,
//                        @PageableDefault(size = 5, sort = "id", direction = Sort.Direction.DESC)
//                        @RequestParam(required = false, defaultValue = "") String search) {
//        return "menu";
//    }
}