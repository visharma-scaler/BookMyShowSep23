package com.scaler.bookmyshowsep23.controllers;

import com.scaler.bookmyshowsep23.dto.BookMovieRequestDto;
import com.scaler.bookmyshowsep23.dto.BookMovieResponseDto;
import com.scaler.bookmyshowsep23.dto.ResponseStatus;
import com.scaler.bookmyshowsep23.models.Booking;
import com.scaler.bookmyshowsep23.services.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class BookingController {

    private BookingService bookingService;

    @Autowired  // Automatically find the objects of the parameters, created it, send it to function ahead
    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    public BookMovieResponseDto bookMovie(BookMovieRequestDto request) {
        Booking booking = bookingService.bookMovie(request.getShowSeatIds(), request.getUserId(), request.getShowId());

        BookMovieResponseDto bookMovieResponseDto = new BookMovieResponseDto();
        bookMovieResponseDto.setBookingId(booking.getId());
        bookMovieResponseDto.setAmount(booking.getAmount());
        bookMovieResponseDto.setStatus(ResponseStatus.SUCCESS);
        return bookMovieResponseDto;
    }
}
