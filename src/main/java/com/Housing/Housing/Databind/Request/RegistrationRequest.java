package com.Housing.Housing.Databind.Request;

import com.Housing.Housing.Databind.Enums.UserRole;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

public class RegistrationRequest {

    private String firstName;
    private String secondName;
    private String emailAddress;
    private String phoneNumber;
    private String userName;
    private String password;
    @Enumerated(EnumType.STRING)
    private UserRole role;


    public String getFirstName() {
        return firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public UserRole getRole() {
      return role;
    }

}
