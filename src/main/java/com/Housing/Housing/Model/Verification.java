package com.Housing.Housing.Model;

import com.Housing.Housing.Databind.Enums.UserRole;
import com.Housing.Housing.Utils.BasicResponse;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Getter
@Setter
@Table(name="verification")

public class Verification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false,name="Id")
    private Long Id;

    @Column(name = "code")
    private String code;

    @Column(name = "expirydate")
    private LocalDateTime expirydate;

    @Column(name = "purpose")
    private String purpose;

    @ManyToOne
    @JoinColumn(name = "app_user_id")
    private AppUser appUser;

    public Verification() {

    }
//this keyword instantiates the class
    public Verification(String code, LocalDateTime expirydate, String purpose, AppUser appUser) {
        this.code = code;
        this.expirydate = expirydate;
        this.purpose = purpose;
        this.appUser = appUser;
    }



}
