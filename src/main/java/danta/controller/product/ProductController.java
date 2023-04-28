package danta.controller.product;

import danta.model.dto.product.ProductDetailDto;
import danta.model.dto.product.ProductRequestDto;
import danta.service.product.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @ApiIgnore
    @GetMapping("/items/new")
    public String getNewItemPage(Model model) {
        model.addAttribute("form", new ProductRequestDto());
        return "items/registerItemForm";
    }

    @ApiIgnore
    @PostMapping("/items/new")
    public String createItem(@ModelAttribute @Valid ProductRequestDto productRequest) {
        Long newItemId = Long.valueOf(productService.saveProduct(productRequest));
        return "redirect:/items/"+ newItemId;
    }

    @ApiIgnore
    @GetMapping("/items/{itemId}")
    public String getItemDetailsPage(@PathVariable("itemId") Long itemId,
                                     Model model) {
        ProductDetailDto productDetails = productService.findItem(itemId);
        model.addAttribute("productDetails", productDetails);
        return "items/itemDetails";
    }
}
