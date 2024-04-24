package com.e9pay.entity;

import com.e9pay.common.util.DatePrefixedSequence;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity
@Getter @Setter
@Table(name = "TB_ADW_CUST_M")
@SecondaryTable(name = "TB_ADW_CUST_SUB_M", pkJoinColumns = @PrimaryKeyJoinColumn(name = "CSTMR_ID"))
public class Customer {

    @Id
    @Column(name = "CSTMR_ID")
    @GeneratedValue(generator = "datePrefixedGenerator")
    @GenericGenerator(
            name = "datePrefixedGenerator",
            type = DatePrefixedSequence.class,
            parameters = {@org.hibernate.annotations.Parameter(name = "sequence_name", value = "sq_cstmr_l"),
                          @org.hibernate.annotations.Parameter(name = "increment_size", value = "1")}
    )
    private String id;

    @Column(name = "FST_CSTMR_NM")
    private String firstName;

    @Column(name = "LST_CSTMR_NM")
    private String lastName;

    @Column(name = "SUB_COLUMN1", table="TB_ADW_CUST_SUB_M")
    private String subColumn1;

    @Column(name = "SUB_COLUMN2", table="TB_ADW_CUST_SUB_M")
    private BigDecimal subColumn2;


}
