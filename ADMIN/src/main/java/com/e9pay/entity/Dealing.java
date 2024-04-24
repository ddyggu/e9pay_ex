package com.e9pay.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Parameter;
import com.e9pay.common.util.DatePrefixedSequence;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import java.math.BigDecimal;


@Entity
@Getter @Setter
@Table(name="TB_ADW_DELNG_L")
public class Dealing {

    @Id
    @Column(name = "DELNG_ID")
    @GeneratedValue(generator = "datePrefixedGenerator")
    @GenericGenerator(
            name = "datePrefixedGenerator",
            type = DatePrefixedSequence.class,
            parameters = {@Parameter(name = "sequence_name", value = "sq_delng_l"),
                          @Parameter(name = "increment_size", value = "1")}
    )
    private String id;

    @Column(name="DEFRAY_AMOUNT")
    private BigDecimal amount;

    @ManyToOne
    @JoinColumn(name="CSTMR_ID")
    private Customer customer;


}
