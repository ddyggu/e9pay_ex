package com.e9pay.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.sql.Date;
import java.time.LocalDate;

@Data
@Entity
@Table(name="tb_adw_admincust_m")
public class AdminUser implements java.io.Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "EMP_ID", nullable = false)
    private String empId;
    @Column(name = "RGST_CANCL_SE_COD")
    private String rgstCanclSeCod;
    @Column(name = "EMPR_SECRET_NO")
    private String emprSecretNo;
    @Column(name = "EMPR_GROUP_COD")
    private String emprGroupCod;
    @Column(name = "OFCPS_COD")
    private String ofcpsCod;
    @Column(name = "FRST_RGST_DTTM")
    private LocalDate frstRgstDttm;
    @Column(name = "FRST_REGISTER_ID")
    private String frstRegisterId;
    @Column(name = "LAST_UPDT_DTTM")
    private LocalDate lastUpdtDttm;
    @Column(name = "LAST_UPDUSR_ID")
    private String lastUpdusrId;

}
