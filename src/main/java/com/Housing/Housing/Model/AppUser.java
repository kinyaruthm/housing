package com.Housing.Housing.Model;

import com.Housing.Housing.Databind.Enums.UserRole;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@Table(name="appuser")
@NoArgsConstructor
public class AppUser implements UserDetails {

    @Id
    @GeneratedValue (strategy= GenerationType.IDENTITY)

    @Column(nullable = false,name="Id")
    private Long Id;

    @Column(name="firstName")
    private String firstName;

    @Column(name="secondName")
    private String secondName;

    @Column(name="email_address")
    private String emailAddress;

    @Column(name="phoneNumber")
    private String phoneNumber;


    @Column(name="userName",unique = true)
    private String userName;

    @Column(name="password")
    private String password;

    private Boolean enabled=false;

    @Enumerated(EnumType.STRING)
    private UserRole role;


    //creating a constructor
    public AppUser(String firstName, String secondName, String emailAddress, String phoneNumber, String userName, String password, UserRole role) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.emailAddress = emailAddress;
        this.phoneNumber = phoneNumber;
        this.userName = userName;
        this.password = password;
        this.role = role;
    }
//Getters and setters ---(lombok library)

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
}
