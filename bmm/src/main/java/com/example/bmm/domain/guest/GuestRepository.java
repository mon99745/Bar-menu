package com.example.bmm.domain.guest;

import org.springframework.data.jpa.repository.JpaRepository;

public interface GuestRepository
        extends JpaRepository<Guest, Long> {
    Guest findGuestById(Long guestId);
}