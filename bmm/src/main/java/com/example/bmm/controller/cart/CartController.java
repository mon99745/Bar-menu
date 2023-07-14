package com.example.bmm.controller.cart;

import com.example.bmm.converter.AuthenticationConverter;
import com.example.bmm.domain.guest.Guest;
import com.example.bmm.domain.user.User;
import com.example.bmm.model.dto.cart.AddToCartRequestFormDto;
import com.example.bmm.model.dto.cart.CartLineDto;
import com.example.bmm.model.dto.cart.ModifyOrderCountRequestFormDto;
import com.example.bmm.service.cart.CartService;
import com.example.bmm.service.guest.GuestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import java.util.List;

@ApiIgnore
@Controller
@RequiredArgsConstructor
public class CartController {
    private final GuestService guestService;
    private final CartService cartService;
    private final AuthenticationConverter authenticationConverter;

    /**
     * 장바구니 목록 조회
     */
    @GetMapping("/cart")
    public String getCartPage(Authentication authentication, Model model, HttpSession session) {
        List<CartLineDto> cartLineDtoInCartPage;

        if(authentication == null) {
            // 게스트의 경우
            Guest guest = guestService.createGuest(session);
            cartLineDtoInCartPage = cartService.getCartInCartPage(guest.getId());
        } else {
            User user = authenticationConverter.getUserFromAuthentication(authentication);
            cartLineDtoInCartPage = cartService.getCartInCartPage(user.getId());
        }
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
    public ResponseEntity modifyCartLine(Authentication authentication, @ModelAttribute ModifyOrderCountRequestFormDto modifyOrderCountRequestForm, HttpSession session) {
        if(authentication == null) {
            // 게스트의 경우
            Guest guest = guestService.createGuest(session);
            cartService.modifyOrderCount(guest.getId(), modifyOrderCountRequestForm);
        } else {
            User user = authenticationConverter.getUserFromAuthentication(authentication);
            cartService.modifyOrderCount(user.getId(), modifyOrderCountRequestForm);
        }
        return ResponseEntity.ok().build();
    }

    /**
     * 장바구니 목록 삭제
     */
    @DeleteMapping("/cart")
    @ResponseBody
    public ResponseEntity deleteCartLine(Authentication authentication, @RequestParam("productId") Long productId, HttpSession session) {
        if(authentication == null) {
            // 게스트의 경우
            Guest guest = guestService.createGuest(session);
            cartService.removeCartLine(guest.getId(), productId);
        } else {

            User user = authenticationConverter.getUserFromAuthentication(authentication);
            cartService.removeCartLine(user.getId(), productId);
        }
        return ResponseEntity.ok().build();
    }
}
