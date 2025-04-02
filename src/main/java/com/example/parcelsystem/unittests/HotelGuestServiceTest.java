package com.example.parcelsystem.unittests;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.example.parcelsystem.HotelGuest;
import com.example.parcelsystem.HotelGuestRepository;
import com.example.parcelsystem.HotelGuestService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
public class HotelGuestServiceTest {

    @Mock
    private HotelGuestRepository hotelGuestRepository;

    @InjectMocks
    private HotelGuestService hotelGuestService;

    @Test
    public void testFindByRoomNumberAndGuestName_GuestExists() {
        // Arrange
        Integer roomNumber = 101;
        String guestName = "Alice";
        HotelGuest mockGuest = new HotelGuest();
        mockGuest.setRoomNumber(roomNumber);
        mockGuest.setGuestName(guestName);

        when(hotelGuestRepository.findByRoomNumberAndGuestName(roomNumber, guestName))
                .thenReturn(Optional.of(mockGuest));

        // Act
        Optional<HotelGuest> result = hotelGuestService.findByRoomNumberAndGuestName(roomNumber, guestName);

        // Assert
        assertNotNull(result);
        assertEquals(guestName, result.get().getGuestName());
        assertEquals(roomNumber, result.get().getRoomNumber());
    }

    @Test
    public void testFindByRoomNumberAndGuestName_GuestDoesNotExist() {
        // Arrange
        Integer roomNumber = 101;
        String guestName = "Alice";

        when(hotelGuestRepository.findByRoomNumberAndGuestName(roomNumber, guestName))
                .thenReturn(Optional.empty());

        // Act & Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            hotelGuestService.findByRoomNumberAndGuestName(roomNumber, guestName);
        });

        // Validate the exception message
        String expectedMessage = "No checked-in guest found with the provided name and room number.";
        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }

}