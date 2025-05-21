package com.Housing.Housing.Service;

import com.Housing.Housing.Databind.Request.PropertyRequest;
import com.Housing.Housing.Model.Property;
import com.Housing.Housing.Repository.PropertyRepository;
import com.Housing.Housing.Utils.BasicResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class PropertyService{
    @Autowired
    private PropertyRepository propertyRepository;
    public BasicResponse addProperty(PropertyRequest property) {
        Property properties = new Property(
                property.getPropertyName(),
                property.getDescription(),
                property.getLocation(),
                property.getPrice()
        );
        propertyRepository.save(properties);
 return BasicResponse.OfSuccess(properties);

    }
}
