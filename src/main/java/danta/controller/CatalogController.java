package danta.controller;

import danta.domain.product.Product;
import danta.model.dto.cart.ProductSearchForm;
import danta.service.product.CatalogService;
import danta.service.product.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

@ApiIgnore
@Controller
@RequiredArgsConstructor
public class CatalogController {
    private final ProductService productService;

    // 완성 후 인덱스와 합칠것

    @GetMapping("/")
    public String getMainPage(String categoryName, @ModelAttribute ProductSearchForm searchForm, Model model) {
//        // category
//        List<CategoryEntity> categorys = categoryService.findAll();
//        model.addAttribute("categorys", categorys);

        // 아이템 검색 form
        if (searchForm == null)
            model.addAttribute("itemSearchForm", new ProductSearchForm());
        else
            model.addAttribute("itemSearchForm", searchForm);

        // 아이템 리스트
        searchForm.setCategoryName(categoryName);
        List<Product> products = productService.findAll();
        model.addAttribute("items", products);

//        searchForm에서의 오류를 못잡음
//        List<CatalogSummary> items = catalogService.getCatalog(searchForm);
//        model.addAttribute("items", items);

        // Todo 정상 작동 시 menu 로 복구
        return "menu";

    }
}
