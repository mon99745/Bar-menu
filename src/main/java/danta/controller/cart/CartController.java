package danta.controller.cart;

import danta.config.auth.PrincipalDetail;
import danta.converter.AuthenticationConverter;
import danta.domain.cart.Cart;
import danta.domain.guest.Guest;
import danta.domain.user.User;
import danta.model.dto.cart.CartLineDto;
import danta.model.dto.cart.AddToCartRequestFormDto;
import danta.service.cart.CartService;
import danta.model.dto.cart.ModifyOrderCountRequestFormDto;
import danta.service.guest.GuestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@ApiIgnore
@Controller
@RequiredArgsConstructor
public class CartController {
    private final GuestService guestService;
    private final CartService cartService;

    private final PrincipalDetail principalDetail;

    private final AuthenticationConverter authenticationConverter;
    /**
     * 장바구니 목록 조회
     */
    @GetMapping("/cart")
    public String getCartPage(Authentication authentication, Model model) {
        if(authentication == null) {}
        User user = authenticationConverter.getUserFromAuthentication(authentication);
        List<CartLineDto> cartLineDtoInCartPage = cartService.getCartInCartPage(user.getId());
        model.addAttribute("cartLineList", cartLineDtoInCartPage);

        return "cart/cart";
    }

    /**
     * 장바구니 목록 추가
     */
    @PostMapping("/cart")
    public String addProductToCart(Authentication authentication, @ModelAttribute @Valid AddToCartRequestFormDto addToCartRequestForm, HttpSession session) {
        if(authentication == null) {
            // 게스트의 경우
            Guest guest = guestService.createGuest(session);
            cartService.addProductToCart(guest.getId(), addToCartRequestForm);
        } else{
            // 회원일 경우
            User user = authenticationConverter.getUserFromAuthentication(authentication);
            cartService.addProductToCart(user.getId(),addToCartRequestForm);
        }

        return "redirect:/cart";
    }

    /**
     * 장바구니 목록 변경
     */
    @PutMapping("/cart")
    @ResponseBody
    public ResponseEntity modifyCartLine(@ModelAttribute ModifyOrderCountRequestFormDto modifyOrderCountRequestForm,
                                         User user) {
        cartService.modifyOrderCount(user.getId(), modifyOrderCountRequestForm);

        return ResponseEntity.ok().build();
    }

    /**
     * 장바구니 목록 삭제
     */
    @DeleteMapping("/cart")
    @ResponseBody
    public ResponseEntity deleteCartLine(@RequestParam("productId") Long productId,
                                         User user) {
        cartService.removeCartLine(user.getId(), productId);
        return ResponseEntity.ok().build();
    }
}
