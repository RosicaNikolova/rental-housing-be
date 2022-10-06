package org.example.business.impl;

import org.example.business.PropertyManager;
import org.example.controller.DTO.PropertyDTO;
import org.example.domain.Responses.GetAllPropertiesResponse;
import org.example.domain.Property;
import org.example.persistence.PropertyRepository;
import org.example.persistence.entity.PropertyEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PropertyManagerImpl implements PropertyManager {

    private PropertyRepository propertyRepository;
    private ModelMapper modelMapper;
    @Override
    public Optional<PropertyDTO> getProperty(long id) {

        Optional<Property> resultFromRepo = propertyRepository.findById(id).map(PropertyConverter::convert);
        PropertyDTO response = modelMapper.map(resultFromRepo.get(), PropertyDTO.class);
        Optional<PropertyDTO> property = Optional.of(response);
        return property;
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

        //Getting properties entities from the repository
        List<PropertyEntity> resultsFromRepo = propertyRepository.findAll();

        //Converting PropertyEntity to Property
        List<Property> propertiesConverted = resultsFromRepo.stream().map(PropertyConverter::convert).toList();

        final GetAllPropertiesResponse response =new GetAllPropertiesResponse();

        //Creating the response object which is List<PropertyDTO>
        response.setProperties(propertiesConverted.stream().map(property -> modelMapper.map(property, PropertyDTO.class)).toList());
        return response;
    }
}
