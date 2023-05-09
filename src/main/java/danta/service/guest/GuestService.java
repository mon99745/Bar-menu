package danta.service.guest;

import danta.domain.cart.Cart;
import danta.domain.guest.Guest;
import danta.domain.guest.GuestRepository;
import danta.service.cart.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpSession;

@Service
public class GuestService {
    private final GuestRepository guestRepository;
    private final CartService cartService;


    /**
     * 게스트 계정 생성
     * Session ID
     */
    @Transactional
    public Guest createGuest(HttpSession session) {

        // Session Id -> Guest Id
        Guest guest = new Guest();
        guest.setId(Long.valueOf(session.getId().hashCode()));
        guest.setStatus(true);

       guestRepository.save(guest);

        // 장바구니 생성
//        Cart cart = new Cart();
//        cart.setId(guest.getId());
        cartService.createCart(guest.getId());

        return guest;
    }


    public Guest findGuestById(Long guestId) {
        return guestRepository.findById(guestId)
                .orElseThrow(() -> new EntityNotFoundException("Guest not found"));
    }

    @Autowired
    public GuestService(GuestRepository guestRepository, CartService cartService) {
        this.guestRepository = guestRepository;
        this.cartService = cartService;
    }
}
