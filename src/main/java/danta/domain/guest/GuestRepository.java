package danta.domain.guest;

import danta.domain.guest.Guest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GuestRepository
        extends JpaRepository<Guest, Long> {
}