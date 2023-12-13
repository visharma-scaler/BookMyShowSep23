package com.scaler.bookmyshowsep23.models;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Payment extends BaseModel{
    private String referenceNumber;
    private int amount;

    @Enumerated(value = EnumType.ORDINAL)
    private PaymentProvider paymentProvider;

    @Enumerated(value = EnumType.ORDINAL)
    private PaymentStatus status;
}
