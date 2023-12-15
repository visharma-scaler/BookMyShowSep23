package com.scaler.bookmyshowsep23.repositories;

import com.scaler.bookmyshowsep23.models.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
}
