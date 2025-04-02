package com.example.parcelsystem;

import java.time.LocalDateTime;

public class ParcelRequest {
    private String guestName;
    private Integer roomNumber;
    private String parcelName;
    private LocalDateTime parcelReceivedTime;

    // Getters and Setters
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

    public String getParcelName() {
        return parcelName;
    }

    public void setParcelName(String parcelName) {
        this.parcelName = parcelName;
    }

    public LocalDateTime getParcelReceivedTime() {
        return parcelReceivedTime;
    }

    public void setParcelReceivedTime(LocalDateTime parcelReceivedTime) {
        this.parcelReceivedTime = parcelReceivedTime;
    }
}
