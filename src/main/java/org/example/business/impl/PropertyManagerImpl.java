package org.example.business.impl;

import lombok.AllArgsConstructor;
import org.example.business.PropertyManager;
import org.example.controller.DTO.PropertyDTO;
import org.example.domain.Responses.GetAllPropertiesResponse;
import org.example.domain.Property;
import org.example.persistence.PropertyRepository;
import org.example.persistence.converters.PropertyConverter;
import org.example.persistence.entity.PropertyEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
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
    public List<Property> getProperties() {
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
        List<Property> resultsFromRepo = propertyRepository.findAll();

        //Converting PropertyEntity to Property

        //Creating the response object which is List<PropertyDTO>
        return resultsFromRepo;
    }
}
