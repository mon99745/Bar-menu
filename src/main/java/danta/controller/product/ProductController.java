package danta.controller.product;

import danta.service.product.ProductDetails;
import danta.service.product.ProductRequest;
import danta.service.product.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;


    @GetMapping("/items/new")
    public String getNewItemPage(Model model) {
        model.addAttribute("form", new ProductRequest());
        return "items/registerItemForm";
    }

    @PostMapping("/items/new")
    public String createItem(@ModelAttribute @Valid ProductRequest productRequest) {
        Long newItemId = Long.valueOf(productService.saveProduct(productRequest));
        return "redirect:/items/"+ newItemId;
    }

    @GetMapping("/items/{itemId}")
    public String getItemDetailsPage(@PathVariable("itemId") Long itemId,
                                     Model model) {
        ProductDetails productDetails = productService.findItem(itemId);
        model.addAttribute("productDetails", productDetails);
        return "items/itemDetails";
    }
}
