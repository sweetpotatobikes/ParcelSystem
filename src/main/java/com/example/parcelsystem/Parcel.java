package com.example.parcelsystem;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "parcels")
public class Parcel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long parcelId;

    private String parcelName;

    private boolean parcelPickedUp;

    private LocalDateTime parcelReceivedTime;

    @ManyToOne
    @JoinColumn(name = "guest_id", nullable = false)
    private HotelGuest guest;

    // Getters and Setters

    public Long getParcelId() {
        return parcelId;
    }

    public void setParcelId(Long parcelId) {
        this.parcelId = parcelId;
    }

    public String getParcelName() {
        return parcelName;
    }

    public void setParcelName(String parcelName) {
        this.parcelName = parcelName;
    }

    public boolean isParcelPickedUp() {
        return parcelPickedUp;
    }

    public void setParcelPickedUp(boolean parcelPickedUp) {
        this.parcelPickedUp = parcelPickedUp;
    }

    public LocalDateTime getParcelReceivedTime() {
        return parcelReceivedTime;
    }

    public void setParcelReceivedTime(LocalDateTime parcelReceivedTime) {
        this.parcelReceivedTime = parcelReceivedTime;
    }

    public HotelGuest getGuest() {
        return guest;
    }

    public void setGuest(HotelGuest guest) {
        this.guest = guest;
    }

    @Transient
    public Integer getRoomNumber() {
        return guest != null ? guest.getRoomNumber() : null;
    }
}
