package com.scaler.bookmyshowsep23.services;

import com.scaler.bookmyshowsep23.models.Show;
import com.scaler.bookmyshowsep23.models.ShowSeat;
import com.scaler.bookmyshowsep23.models.ShowSeatType;
import com.scaler.bookmyshowsep23.repositories.ShowSeatTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PriceCalculatorService {

    private ShowSeatTypeRepository showSeatTypeRepository;

    @Autowired
    public PriceCalculatorService(ShowSeatTypeRepository showSeatTypeRepository) {
        this.showSeatTypeRepository = showSeatTypeRepository;
    }

    public int calculatePrice(Show show, List<ShowSeat> showSeats) {
        /*
            1. Get ShowSeatType for that show
            2. Get SeatType for all the seats
            3. Add the amount for all the seats
        */


        List<ShowSeatType> showSeatTypes = showSeatTypeRepository.findAllByShow(show);

        int amount = 0;

        for (ShowSeat showSeat : showSeats) {
            for (ShowSeatType showSeatType : showSeatTypes) {
                if (showSeatType.getSeatType().equals(showSeat.getSeat().getSeatType())) {
                    amount += showSeatType.getPrice();
                    break;
                }
            }
        }


        return amount;
    }
}
