package org.example.business.impl;

import org.example.business.PropertyManager;
import org.example.domain.GetAllPropertiesResponse;
import org.example.domain.Property;
import org.example.domain.PropertyType;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PropertyManagerImpl implements PropertyManager {

    @Override
    public Property getProperty() {
        Property property = new Property();
        property.setId(1);
        property.setPropertyType(PropertyType.APARTMENT);
        property.setCity("Eindhoven");
        property.setStreet("Pizanostraat");
        property.setPrice(950);
        property.setLivingSpace(90);
        property.setStreetNumber(72);
        property.setNumberOfBedrooms(2);
        property.setNumberOfRooms(4);
        return property;
    }

    @Override
    public GetAllPropertiesResponse getProperties() {
        List<Property> properties = new ArrayList<>();
        Property property1 = new Property();
        property1.setId(1);
        property1.setCity("Eindhoven");
        Property property2 = new Property();
        property1.setId(2);
        property2.setCity("Eindhoven");

        properties.add(property1);
        properties.add(property2);

        GetAllPropertiesResponse getAllPropertiesResponse = new GetAllPropertiesResponse(properties);
        return getAllPropertiesResponse;
    }
}
