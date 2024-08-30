package com.sparta.spartaeats.payments.dto;

import com.querydsl.core.annotations.QueryProjection;
import com.sparta.spartaeats.types.PaymentStatus;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
 @NoArgsConstructor
public class PaymentResponseDto {

    private UUID paymentId;
    private Integer price;
    private PaymentStatus status;

    @QueryProjection
    public PaymentResponseDto(UUID paymentId, Integer price, PaymentStatus status) {
        this.paymentId = paymentId;
        this.price = price;
        this.status = status;
    }
}