package com.scaler.bookmyshowsep23.services;

import com.scaler.bookmyshowsep23.models.Booking;
import com.scaler.bookmyshowsep23.repositories.BookingRepository;
import com.scaler.bookmyshowsep23.repositories.ShowRepository;
import com.scaler.bookmyshowsep23.repositories.ShowSeatRepository;
import com.scaler.bookmyshowsep23.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BookingService {

    private UserRepository userRepository;
    private ShowRepository showRepository;
    private ShowSeatRepository showSeatRepository;
    private BookingRepository bookingRepository;

    @Autowired
    public BookingService(UserRepository userRepository,
                          ShowRepository showRepository,
                          ShowSeatRepository showSeatRepository,
                          BookingRepository bookingRepository) {
        this.userRepository = userRepository;
        this.showRepository = showRepository;
        this.showSeatRepository = showSeatRepository;
        this.bookingRepository = bookingRepository;
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public Booking bookMovie(List<Long> showSeatIds, Long userId, Long showId) {

        /*
            ----------------- For today, start transaction ---------------
            1. Get the user using the userId
            2. Get the show using the showId
            ----------------- Take Lock ---------------
            3. Fetch the ShowSeats using the showSeatIds
            4. Check if all the seats are available or not
            5. If not, throw error
            6. If yes, mark the show seats status as LOCKED
            7. Save the updated show seats in the database
            ----------------- Release Lock ---------------
            8. Create a booking object
            9. Return the booking object
            ----------------- end  ---------------
        */
        return null;
    }
}
