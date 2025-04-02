package com.example.parcelsystem;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface HotelGuestRepository extends JpaRepository<HotelGuest, Long> {
    List<HotelGuest> findByCheckoutTimeIsNull();

    Optional<HotelGuest> findByGuestName(String guestName);

    Optional<HotelGuest> findByRoomNumberAndGuestName(Integer roomNumber, String guestName);
}