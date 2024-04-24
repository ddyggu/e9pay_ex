package com.e9pay.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Embeddable
@Getter @Setter
public class Exchange {

    @Column(name="exconv_amount")
    private BigDecimal usdAmount;

    @Column(name="ehgt_usd_rate")
    private BigDecimal usdRate;

}
