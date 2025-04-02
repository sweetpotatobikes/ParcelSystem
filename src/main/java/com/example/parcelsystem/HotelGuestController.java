package com.example.parcelsystem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/guests")
public class HotelGuestController {
    @Autowired
    private HotelGuestService guestService;

    @GetMapping("/checked-in")
    public List<HotelGuest> getCheckedInGuests() {
        return guestService.getCheckedInGuests();
    }
    @GetMapping("/all")
    public List<HotelGuest> getAllGuests(){
        return guestService.getAllGuests();
    }
}
