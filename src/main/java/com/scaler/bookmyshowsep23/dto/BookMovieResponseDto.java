package com.scaler.bookmyshowsep23.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookMovieResponseDto {
    private ResponseStatus status;
    private int amount;
    private Long bookingId;
}
