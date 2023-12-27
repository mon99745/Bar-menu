package com.example.bmm.service.guest;

import com.example.bmc.exception.BmcException;
import com.example.bmc.exception.BmmError;
import com.example.bmm.domain.guest.Guest;
import com.example.bmm.domain.guest.GuestRepository;
import com.example.bmm.model.enums.Role;
import com.example.bmm.service.cart.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpSession;

import java.util.Optional;

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

        String sessionId = session.getId();
        Long guestId = Long.valueOf(sessionId.hashCode());

        // 기존 게스트 엔티티 조회
        Guest guest = guestRepository.findById(guestId).orElse(null);

        if (guest == null) {
            // 기존 게스트 엔티티가 없으면 새로운 게스트 엔티티 생성
            guest = new Guest();
            guest.setId(guestId);
            guest.setStatus(true);
            guest.setRole(Role.GUEST);

            guestRepository.save(guest);

            // 장바구니 생성
            cartService.createCart(guest.getId());
        }

        return guest;
    }


    public Guest findGuestById(Long guestId) {
        return guestRepository.findById(guestId).orElseThrow(() -> new EntityNotFoundException("Guest not found"));
    }

    public Guest findGuest(Long id) {
        Guest guest = validateExistMember(guestRepository.findById(id));

        return guest;
    }

    /**
     * 게스트 예외검증
     */
    private Guest validateExistMember(Optional<Guest> guest) {
        if(!guest.isPresent()){
            throw new BmcException(BmmError.BMM_GUEST_INFO_NOT_EXIST, null);
        }
        return guest.get();
    }

    @Autowired
    public GuestService(GuestRepository guestRepository, CartService cartService) {
        this.guestRepository = guestRepository;
        this.cartService = cartService;
    }
}
