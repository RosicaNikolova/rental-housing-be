package org.example.business.impl;

import lombok.AllArgsConstructor;
import org.example.business.PropertyManager;
import org.example.domain.AccessToken;
import org.example.domain.Property;
import org.example.persistence.PropertyRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PropertyManagerImpl implements PropertyManager {
    private final PropertyRepository propertyRepository;
    private AccessToken AccessToken;


    @Override
    public Optional<Property> getProperty(long id) {

        return propertyRepository.findById(id);
    }

    @Override
    public List<Property> getProperties() {
        return  propertyRepository.findAll();
    }

    @Override
    public Long createProperty(Property request){
            request.setIdHomeowner(AccessToken.getUserId());
            return propertyRepository.createProperty(request);
    }

    @Override
    public void updateProperty(Property property) {
            propertyRepository.updateProperty(property);
    }

    @Override
    public void deleteProperty(long propertyId) {

            propertyRepository.deleteProperty(propertyId);
    }
}
