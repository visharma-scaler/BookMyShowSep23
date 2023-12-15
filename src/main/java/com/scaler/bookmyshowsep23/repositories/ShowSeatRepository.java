package com.scaler.bookmyshowsep23.repositories;

import com.scaler.bookmyshowsep23.models.ShowSeat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShowSeatRepository extends JpaRepository<ShowSeat, Long> {
}
