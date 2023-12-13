package com.scaler.bookmyshowsep23.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
public class ShowSeat extends BaseModel {

    @ManyToOne
    private MovieShow movieShow;

    @ManyToOne
    private Seat seat;

    @Enumerated(value = EnumType.ORDINAL)
    private ShowSeatStatus status;
    private Date blockedAt;
}
