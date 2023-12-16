package com.scaler.bookmyshowsep23;

import com.scaler.bookmyshowsep23.controllers.UserController;
import com.scaler.bookmyshowsep23.dto.LoginRequestDto;
import com.scaler.bookmyshowsep23.dto.LoginResponseDto;
import com.scaler.bookmyshowsep23.dto.SignUpRequestDto;
import com.scaler.bookmyshowsep23.dto.SignUpResponseDto;
import com.scaler.bookmyshowsep23.models.BaseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.Date;

@EnableJpaAuditing      // This helps us to triggers the events to populate the audit fields
@SpringBootApplication
public class BookMyShowSep23Application implements CommandLineRunner {

    @Autowired
    private UserController userController;

    public static void main(String[] args) {
        SpringApplication.run(BookMyShowSep23Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
//        SignUpRequestDto request = new SignUpRequestDto();
        LoginRequestDto request = new LoginRequestDto();
        request.setEmail("muhilan@scaler.com");
        request.setPassword("1234");
        // zrIRcQf0ODuhB520qOvBMz9AhShOOCjUgudaBdafFkpbvUAGS

//        SignUpResponseDto responseDto = userController.signUp(request);

        LoginResponseDto responseDto = userController.login(request);

        System.out.println(responseDto.getStatus() + " " + responseDto.getUserId());
    }
}


/*
        ORM -> Object Relational Mapping
*/
