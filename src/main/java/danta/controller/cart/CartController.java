package danta.controller.cart;

import danta.model.User;
import danta.model.CartLine;
import danta.service.cart.form.AddToCartRequestForm;
import danta.service.cart.CartService;
import danta.service.cart.form.ModifyOrderCountRequestForm;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;
import java.util.List;

@ApiIgnore
@Controller
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;

    /**
     * 장바구니 목록 조회
     */
    @GetMapping("/carts")
    public String getCartPage(User user, Model model) {
        Long userId = user.getId();
        List<CartLine> cartLineDtoInCartPage = cartService.getCartInCartPage(userId);
        model.addAttribute("cartLineList", cartLineDtoInCartPage);

        return "carts/cart";
    }

    /**
     * 장바구니 목록 추가
     */
    @PostMapping("/carts")
    public String addProductToCart(@ModelAttribute @Valid AddToCartRequestForm addToCartRequestForm,
                                User user) {
        cartService.addProductToCart(user.getId(), addToCartRequestForm);
        return "redirect:/carts";
    }

    /**
     * 장바구니 목록 변경
     */
    @PutMapping("/carts")
    @ResponseBody
    public ResponseEntity modifyCartLine(@ModelAttribute ModifyOrderCountRequestForm modifyOrderCountRequestForm,
                                         User user) {
        cartService.modifyOrderCount(user.getId(), modifyOrderCountRequestForm);

        return ResponseEntity.ok().build();
    }

    /**
     * 장바구니 목록 삭제
     */
    @DeleteMapping("/carts")
    @ResponseBody
    public ResponseEntity deleteCartLine(@RequestParam("productId") Long productId,
                                         User user) {
        cartService.removeCartLine(user.getId(), productId);
        return ResponseEntity.ok().build();
    }
}
