package com.example.parcelsystem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/parcels")
public class ParcelController {

    private final ParcelService parcelService;

    @Autowired
    public ParcelController(ParcelService parcelService) {
        this.parcelService = parcelService;
    }

    @PostMapping("/accept")
    public String acceptParcel(@RequestBody ParcelRequest parcelRequest) {
        try {
            // Extract fields from the JSON body
            parcelService.acceptParcel(
                    parcelRequest.getGuestName(),
                    parcelRequest.getRoomNumber(),
                    parcelRequest.getParcelName(),
                    parcelRequest.getParcelReceivedTime()
            );
            return "Parcel accepted successfully!";
        } catch (Exception e) {
            return "Failed to accept parcel: " + e.getMessage();
        }
    }

    @GetMapping("/unpicked")
    public List<Parcel> getUnpickedParcels(@RequestParam Integer roomNumber) {
        return parcelService.getUnpickedParcelsByRoomNumber(roomNumber);
    }
}
