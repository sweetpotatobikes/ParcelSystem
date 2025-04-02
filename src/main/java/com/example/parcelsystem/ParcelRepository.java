package com.example.parcelsystem;

import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ParcelRepository extends JpaRepository<Parcel, Long> {

    // Query to find parcels by room number with pessimistic locking
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT p FROM Parcel p WHERE p.guest.roomNumber = :roomNumber AND p.parcelPickedUp = false")
    List<Parcel> findUnpickedParcelsByRoomNumber(@Param("roomNumber") Integer roomNumber);

}

