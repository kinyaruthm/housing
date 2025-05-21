package com.Housing.Housing.Databind.Request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PropertyRequest {
    private String propertyName;
    private String location;
    private String description;
    private String price;
}
