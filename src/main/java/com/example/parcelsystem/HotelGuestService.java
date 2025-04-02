package com.example.parcelsystem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HotelGuestService {
    @Autowired
    private HotelGuestRepository guestRepository;

    public List<HotelGuest> getCheckedInGuests() {
        return guestRepository.findByCheckoutTimeIsNull();
    }

    public List<HotelGuest> getAllGuests() {
        return guestRepository.findAll();
    }

    public Optional<HotelGuest> findByRoomNumberAndGuestName(Integer roomNumber, String guestName) {
        Optional<HotelGuest> guestOptional = guestRepository.findByRoomNumberAndGuestName(roomNumber, guestName);
        if (guestOptional.isEmpty()) {
            throw new IllegalArgumentException("No checked-in guest found with the provided name and room number.");
        }
        return guestOptional;
    }

}
