package com.scaler.bookmyshowsep23.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
public class MovieShow extends BaseModel {
    @ManyToOne
    private Movie movie;
    private Date startTime;
    private Date endTime;

    @ManyToOne
    private Screen screen;

    @Enumerated(value = EnumType.ORDINAL)
    @ElementCollection
    private List<Feature> features;
}
