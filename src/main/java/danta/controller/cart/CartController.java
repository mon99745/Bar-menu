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
        Long userId = authenticationConverter.getUserFromAuthentication(authentication).getId();
        List<CartLineDto> cartLineDtoInCartPage = cartService.getCartInCartPage(userId);
        model.addAttribute("cartLineList", cartLineDtoInCartPage);

        return "cart/cart";
    }

    /**
     * 장바구니 목록 추가
     */
    @PostMapping("/cart")
    public String addProductToCart(Authentication authentication, @ModelAttribute @Valid AddToCartRequestFormDto addToCartRequestForm, HttpSession session) {
        Long userId = authenticationConverter.getUserFromAuthentication(authentication).getId();
        if(userId == null) {
            // 게스트의 경우
            // 게스트 ID 생성
            Guest guest = guestService.createGuest();
            Long guestId = guest.getId();

            // 장바구니 객체 생성 및 게스트 ID 할당
            Cart cart = new Cart();
            cart.setId(guestId);

            // 장바구니에 아이템 추가
            cartService.addProductToCart(guestId, addToCartRequestForm);
        } else{
            // 회원일 경우
            cartService.addProductToCart(userId,addToCartRequestForm);
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
