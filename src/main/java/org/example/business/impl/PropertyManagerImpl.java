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

    @Override
    public Optional<Property> getProperty(long id) {


        return propertyRepository.findById(id);
    }

    @Override
    public List<Property> getProperties() {
        return  propertyRepository.findAll();
    }

    @Override
    public Long createProperty(Property request) {

        if(propertyRepository.findByPostCodeAndPrice(request.getPostCode(), request.getPrice())){
            return propertyRepository.createProperty(request);
        }

        return 0L;

    }
}
