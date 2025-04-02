package com.example.parcelsystem;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ParcelService {

    private final ParcelRepository parcelRepository;
    private final HotelGuestRepository hotelGuestRepository;

    public ParcelService(ParcelRepository parcelRepository, HotelGuestRepository hotelGuestRepository) {
        this.parcelRepository = parcelRepository;
        this.hotelGuestRepository = hotelGuestRepository;
    }

    @Transactional(readOnly = true)
    public List<Parcel> getUnpickedParcelsByRoomNumber(Integer roomNumber) {
        return parcelRepository.findUnpickedParcelsByRoomNumber(roomNumber);
    }

    @Transactional
    public Parcel acceptParcel(String guestName, Integer roomNumber, String parcelName, LocalDateTime parcelReceivedTime) {
        // Validate that the guest name matches the room number and guest is checked in
        Optional<HotelGuest> guestOptional = hotelGuestRepository.findByRoomNumberAndGuestName(roomNumber, guestName);
        if (guestOptional.isEmpty()) {
            throw new IllegalArgumentException("No checked-in guest found with the provided name and room number.");
        }

        // Validate that the guest has not checked out yet
        HotelGuest guest = guestOptional.get();
        if (guest.getCheckoutTime() != null) {
            throw new IllegalArgumentException("The guest has already checked out.");
        }

        // Create and save the parcel if validation passes
        Parcel newParcel = new Parcel();
        newParcel.setParcelName(parcelName);
        newParcel.setGuest(guest);
        newParcel.setParcelReceivedTime(parcelReceivedTime);
        newParcel.setParcelPickedUp(false);

        parcelRepository.save(newParcel);

        return newParcel;
    }
}