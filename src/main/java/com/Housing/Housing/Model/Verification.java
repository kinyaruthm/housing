package com.Housing.Housing.Model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name="verification")
public class Verification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false,name="Id")
    private Long Id;

    @Column(name = "code")
    private String code;

    @Column(name = "time")
    private LocalDate time;

    @Column(name = "expirydate")
    private Date expirydate;

    @Column(name = "purpose")
    private String purpose;

    public Verification() {

    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public LocalDate getTime() {
        return time;
    }

    public void setTime(LocalDate time) {
        this.time = time;
    }

    public Date getExpirydate() {
        return expirydate;
    }

    public void setExpirydate(Date expirydate) {
        this.expirydate = expirydate;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public Verification(String code, LocalDate time, Date expirydate, String purpose) {
        this.code = code;
        this.time = time;
        this.expirydate = expirydate;
        this.purpose = purpose;
    }
}
