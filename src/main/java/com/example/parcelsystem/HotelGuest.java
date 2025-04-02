package com.example.parcelsystem;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "hotel_guests")
public class HotelGuest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long guestId;

    private String guestName;

    @Column(unique = true, nullable = false) // Room number must be unique
    private Integer roomNumber;

    private LocalDateTime checkinTime; // Field for check-in time
    private LocalDateTime checkoutTime; // Field for check-out time

    // Getters and Setters

    public Long getGuestId() {
        return guestId;
    }

    public void setGuestId(Long guestId) {
        this.guestId = guestId;
    }

    public String getGuestName() {
        return guestName;
    }

    public void setGuestName(String guestName) {
        this.guestName = guestName;
    }

    public Integer getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(Integer roomNumber) {
        this.roomNumber = roomNumber;
    }

    public LocalDateTime getCheckinTime() {
        return checkinTime;
    }

    public void setCheckinTime(LocalDateTime checkinTime) {
        this.checkinTime = checkinTime;
    }

    public LocalDateTime getCheckoutTime() {
        return checkoutTime;
    }

    public void setCheckoutTime(LocalDateTime checkoutTime) {
        this.checkoutTime = checkoutTime;
    }
}
