package danta.service.guest;

import danta.domain.cart.Cart;
import danta.domain.guest.Guest;
import danta.domain.guest.GuestRepository;
import danta.domain.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.UUID;

@Service
public class GuestService {
    private final GuestRepository guestRepository;

    /**
     * 게스트 계정 생성
     * - 임시 ID 발급
     */
    @Transactional
    public Guest createGuest() {
        // 랜덤값 생성
        UUID uuid = UUID.randomUUID();
        long longValueUuid = Long.parseUnsignedLong(
                uuid.toString().replace("-", ""), 16);

        // 생성한 UUID로 게스트 객체 생성 및 저장
        Guest guest = new Guest();
        guest.setId(longValueUuid);
        guest.setStatus(true);

        guestRepository.save(guest);

        return guest;
    }


    public Guest findGuestById(Long guestId) {
        return guestRepository.findById(guestId)
                .orElseThrow(() -> new EntityNotFoundException("Guest not found"));
    }

    @Autowired
    public GuestService(GuestRepository guestRepository) {
        this.guestRepository = guestRepository;
    }
}
