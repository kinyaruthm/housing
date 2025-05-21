package com.Housing.Housing.Model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="property")
public class Property {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(nullable = false,name="propertyId")
    private Long propertyId;

    @Column(name="PropertyName")
    private String propertyName;

    @Column(name="Location")
    private String location;

    @Column(name = "Description")
    private String description;

    @Column(name = "Price")
    private String price;


    public Property() {

    }

    public Property(String propertyName, String location, String description, String price) {
        this.propertyName = propertyName;
        this.location = location;
        this.description = description;
        this.price = price;
    }

    protected static void add(Property properties) {
    }

    public Long getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(Long propertyId) {
        this.propertyId = propertyId;
    }

    public String getPropertyName() {
        return propertyName;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void add(int i, String bedsitter, String freeWifi, String kiambu, String number) {
    }
}
