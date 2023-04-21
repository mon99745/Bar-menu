package danta.controller.cart;

import danta.service.cart.AddToCartRequestForm;
import danta.service.cart.CartService;
import danta.service.cart.ModifyOrderCountRequestForm;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationConverter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;
    private final AuthenticationConverter authenticationConverter;

    @GetMapping("/carts")
    public String getCartPage(Authentication authentication, Model model) {
//        Long authId = authenticationConverter.getUserFromAuthentication(authentication)
//                .getAuthId();
//        List<CartLineDto> cartLineDtoInCartPage = cartService.getCartInCartPage(authId);
//        model.addAttribute("cartLineList", cartLineDtoInCartPage);

        return "carts/cart";
    }

    @PostMapping("/carts")
    public String addItemToCart(@ModelAttribute @Valid AddToCartRequestForm addToCartRequestForm,
                                Authentication authentication) {
//        User user = authenticationConverter.getUserFromAuthentication(authentication);
//
//        cartService.addItemToCart(user.getAuthId(), addToCartRequestForm);

        return "redirect:/carts";
    }

    @PutMapping("/carts")
    @ResponseBody
    public ResponseEntity modifyCartLine(@ModelAttribute ModifyOrderCountRequestForm modifyOrderCountRequestForm,
                                         Authentication authentication) {
//        User user = authenticationConverter.getUserFromAuthentication(authentication);
//
//        cartService.modifyOrderCount(user.getAuthId(), modifyOrderCountRequestForm);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/carts")
    @ResponseBody
    public ResponseEntity deleteCartLine(@RequestParam("itemId") Long itemId,
                                         Authentication authentication) {
//        User user = authenticationConverter.getUserFromAuthentication(authentication);
//        cartService.removeCartLine(user.getAuthId(), itemId);
        return ResponseEntity.ok().build();
    }
}
