package com.scaler.bookmyshowsep23.dto;

import com.scaler.bookmyshowsep23.models.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginResponseDto {
    private ResponseStatus status;
    private Long userId;
}
