package danta.controller;

import danta.domain.product.Product;
import danta.model.dto.cart.ProductSearchForm;
import danta.service.product.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.view.RedirectView;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

import static danta.domain.product.QProduct.product;

@ApiIgnore
@Controller
@RequiredArgsConstructor
public class CatalogController {
    private final ProductService productService;

    // TODO : 검색기능 에러 수정 작업

    @GetMapping("/")
    public String getMainPage(String category, @ModelAttribute ProductSearchForm searchForm, Model model) {
//        // category
//        List<CategoryEntity> categorys = categoryService.findAll();
//        model.addAttribute("categorys", categorys);

        // 아이템 검색 form
        if (searchForm == null)
            model.addAttribute("productSearchForm", new ProductSearchForm());
        else
            model.addAttribute("productSearchForm", searchForm);

        // 아이템 리스트
        searchForm.setCategory(category);
        List<Product> products = productService.findAll();
        model.addAttribute("products", products);

//        searchForm에서의 오류를 못잡음
//        List<CatalogSummary> products = catalogService.getCatalog(searchForm);
//        model.addAttribute("product", product);

        return "catalog";

    }
    @GetMapping("api")
    public Object swaggerApi() {
        return new RedirectView("swagger-ui/");
    }
}
