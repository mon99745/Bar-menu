package danta.controller.item;

import danta.domain.item.ItemEntity;
import danta.service.item.CatalogService;
import danta.service.item.ItemSearchForm;
import danta.service.item.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class CatalogController {
    private final CatalogService catalogService;
    private final ItemService itemService;

    // 완성 후 인덱스와 합칠것

    @GetMapping("/")
    public String getMainPage(String categoryName, @ModelAttribute ItemSearchForm searchForm, Model model) {
//        // category
//        List<CategoryEntity> categorys = categoryService.findAll();
//        model.addAttribute("categorys", categorys);

        // 아이템 검색 form
        if (searchForm == null)
            model.addAttribute("itemSearchForm", new ItemSearchForm());
        else
            model.addAttribute("itemSearchForm", searchForm);

        // 아이템 리스트
        searchForm.setCategoryName(categoryName);
        List<ItemEntity> items = itemService.findAll();
        model.addAttribute("items", items);

//        searchForm에서의 오류를 못잡음
//        List<CatalogSummary> items = catalogService.getCatalog(searchForm);
//        model.addAttribute("items", items);

        // Todo 정상 작동 시 menu 로 복구
        return "test";

    }
}
