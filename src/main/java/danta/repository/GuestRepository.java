package danta.repository;

import danta.model.guest.Guest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GuestRepository
        extends JpaRepository<Guest, Long> {
}