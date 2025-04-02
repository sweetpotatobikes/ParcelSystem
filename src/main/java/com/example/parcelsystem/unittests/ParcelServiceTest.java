package com.example.parcelsystem.unittests;

import com.example.parcelsystem.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class ParcelServiceTest {

    @Mock
    private ParcelRepository parcelRepository;

    @Mock
    private HotelGuestRepository hotelGuestRepository;

    @InjectMocks
    private ParcelService parcelService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this); // Initialize mocks
    }

    @Test
    public void testAcceptParcel_GuestCheckedIn() {
        // Arrange
        String guestName = "TestGuest";
        int roomNumber = 1;
        LocalDateTime parcelReceivedTime = LocalDateTime.now();

        // Create mock guest
        HotelGuest guest = new HotelGuest();
        guest.setGuestName(guestName);
        guest.setRoomNumber(roomNumber);
        guest.setCheckoutTime(null); // Guest is checked in (not checked out)

        // Create expected parcel
        Parcel parcel = new Parcel();
        parcel.setGuest(guest);
        parcel.setParcelReceivedTime(parcelReceivedTime);
        parcel.setParcelPickedUp(false);

        // Mock repository behavior
        when(hotelGuestRepository.findByRoomNumberAndGuestName(roomNumber, guestName))
                .thenReturn(Optional.of(guest)); // Guest exists in the repository
        when(parcelRepository.save(any(Parcel.class))).thenReturn(parcel); // Parcel is saved

        // Act
        Parcel savedParcel = parcelService.acceptParcel(guestName, roomNumber, "TestParcel", parcelReceivedTime);

        // Assert
        assertNotNull(savedParcel); // Ensure the returned parcel is not null
        assertEquals(guestName, savedParcel.getGuest().getGuestName()); // Validate guest name
        assertEquals(roomNumber, savedParcel.getRoomNumber()); // Validate room number
        assertEquals(parcelReceivedTime, savedParcel.getParcelReceivedTime()); // Validate received time
        assertFalse(savedParcel.isParcelPickedUp()); // Validate pickup status
        verify(parcelRepository, times(1)).save(any(Parcel.class)); // Ensure save is called once
    }


    @Test
    public void testAcceptParcel_GuestCheckedOut() {
        Parcel parcel = new Parcel();
        String guestName = "testCheckedOutGuest";
        int roomNumber = 1;
        HotelGuest guest = new HotelGuest();
        guest.setGuestName(guestName);
        guest.setRoomNumber(roomNumber);
        guest.setCheckoutTime(java.time.LocalDateTime.now());
        parcel.setGuest(guest);

        when(hotelGuestRepository.findByGuestName(guestName)).thenReturn(Optional.of(guest));

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            parcelService.acceptParcel(
                    guestName,
                    roomNumber,
                    "testParcel",
                    LocalDateTime.now());
        });

        assertEquals("No checked-in guest found with the provided name and room number.", exception.getMessage());
        verify(parcelRepository, never()).save(any());
    }
}
