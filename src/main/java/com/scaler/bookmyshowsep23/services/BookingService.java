package com.scaler.bookmyshowsep23.services;

import com.scaler.bookmyshowsep23.models.*;
import com.scaler.bookmyshowsep23.repositories.BookingRepository;
import com.scaler.bookmyshowsep23.repositories.ShowRepository;
import com.scaler.bookmyshowsep23.repositories.ShowSeatRepository;
import com.scaler.bookmyshowsep23.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class BookingService {

    private UserRepository userRepository;
    private ShowRepository showRepository;
    private ShowSeatRepository showSeatRepository;
    private BookingRepository bookingRepository;
    private PriceCalculatorService priceCalculatorService;

    @Autowired
    public BookingService(UserRepository userRepository,
                          ShowRepository showRepository,
                          ShowSeatRepository showSeatRepository,
                          BookingRepository bookingRepository,
                          PriceCalculatorService priceCalculatorService) {
        this.userRepository = userRepository;
        this.showRepository = showRepository;
        this.showSeatRepository = showSeatRepository;
        this.bookingRepository = bookingRepository;
        this.priceCalculatorService = priceCalculatorService;
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
            9. Save the booking object
            10. Return the saved booking object
            ----------------- end  ---------------
        */

        // Step 1
        Optional<User> userOptional = userRepository.findById(userId);

        if (userOptional.isEmpty()) {
            throw new RuntimeException("User not found");
        }

        User bookedBy = userOptional.get();


        // Step 2
        Optional<Show> showOptional = showRepository.findById(showId);

        if (showOptional.isEmpty()) {
            throw new RuntimeException("Show not found");
        }

        Show bookedShow = showOptional.get();

        // Step 3
        List<ShowSeat> showSeats = showSeatRepository.findAllById(showSeatIds);

        // Step 4

        for (ShowSeat showSeat : showSeats) {
            if (!isShowSeatAvailable(showSeat)) {
                throw new RuntimeException("Some of the show seats are not available!");
            }

            showSeat.setStatus(ShowSeatStatus.BLOCKED);
        }

        List<ShowSeat> updateShowSeats = showSeatRepository.saveAll(showSeats);

        Booking booking = new Booking();
        booking.setBookingStatus(BookingStatus.PENDING);
        booking.setShowSeats(updateShowSeats);
        booking.setUser(bookedBy);
        booking.setBookedAt(new Date());
        booking.setShow(bookedShow);
        booking.setAmount(priceCalculatorService.calculatePrice(bookedShow, updateShowSeats));
        booking.setPayments(new ArrayList<>());

        return bookingRepository.save(booking);
    }

    private boolean isShowSeatAvailable(ShowSeat showSeat) {
        return showSeat.getStatus().equals(ShowSeatStatus.AVAILABLE) ||
                (showSeat.getStatus().equals(ShowSeatStatus.BLOCKED) &&
                        ChronoUnit.MINUTES.between(new Date().toInstant(), showSeat.getBlockedAt().toInstant()) > 15);
    }
}
