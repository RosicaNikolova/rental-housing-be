package org.example.business.impl;

import org.example.business.PropertyManager;
import org.example.domain.GetAllPropertiesResponse;
import org.example.domain.Property;
import org.example.domain.PropertyType;
import org.example.persistence.PropertyRepository;
import org.example.persistence.entity.PropertyEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PropertyManagerImpl implements PropertyManager {


    private PropertyRepository propertyRepository;
    @Override
    public Optional<Property> getProperty(long id) {

        Optional<Property> resultFromRepo = propertyRepository.findById(id).map(PropertyConverter::convert);
        return resultFromRepo;
    }

    @Override
    public GetAllPropertiesResponse getProperties() {
       /*
       List<Property> properties = new ArrayList<>();
        Property property1 = new Property();
        property1.setId(1);
        property1.setCity("Eindhoven");
        Property property2 = new Property();
        property1.setId(2);
        property2.setCity("Eindhoven");

        properties.add(property1);
        properties.add(property2);
        */

        //Getting properties from the repository
        List<PropertyEntity> resultsFromRepo = propertyRepository.findAll();
        //Converting PropertyEntity to Property
        List<Property> propertiesConverted =resultsFromRepo.stream().map(PropertyConverter::convert).toList();

        final GetAllPropertiesResponse response =new GetAllPropertiesResponse();
        //Creating the response object which is List<Property>
        response.setProperties(propertiesConverted);
        return response;
    }
}
