package com.e9pay.entity;

import com.e9pay.common.util.DatePrefixedSequence;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Getter @Setter
@Table(name="TB_ADW_OVSEREMIT_L")
public class OutboundRemittance {

    @Id
    @Column(name = "ovsea_delng_id")
    @GeneratedValue(generator = "datePrefixedGenerator")
    @GenericGenerator(
            name = "datePrefixedGenerator",
            type = DatePrefixedSequence.class,
            parameters = {@org.hibernate.annotations.Parameter(name = "sequence_name", value = "sq_ovsea_delng_l"),
                    @org.hibernate.annotations.Parameter(name = "increment_size", value = "1")}
    )
    private String id;

    @OneToOne
    @JoinColumn(name = "DELNG_ID")
    private Dealing dealing;

    @Embedded
    Exchange exchange;

}
