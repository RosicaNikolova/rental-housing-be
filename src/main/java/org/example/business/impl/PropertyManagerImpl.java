package org.example.business.impl;

import lombok.AllArgsConstructor;
import org.example.business.Exceptions.CreatePropertyException;
import org.example.business.Exceptions.DeletePropertyException;
import org.example.business.Exceptions.UpdatePropertyException;
import org.example.business.PropertyManager;
import org.example.domain.Property;
import org.example.persistence.PropertyRepository;
import org.springframework.stereotype.Service;

import java.security.cert.CertificateRevokedException;
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
    public Long createProperty(Property request){

        if(propertyRepository.findByPostCodeAndPrice(request.getPostCode(), request.getPrice())){
            return propertyRepository.createProperty(request);
        }
        else{
            throw new CreatePropertyException("This property already exists");
        }
    }

    @Override
    public void updateProperty(Property property) {
        if(propertyRepository.findById(property.getId()).isEmpty()){
            throw new UpdatePropertyException("Property not found");
        }
        else {
            propertyRepository.updateProperty(property);
        }
    }

    @Override
    public void deleteProperty(long propertyId) {
        if(propertyRepository.findById(propertyId).isEmpty()){
            throw new DeletePropertyException("Property not found");
        }
        else {
            propertyRepository.deleteProperty(propertyId);
        }
    }
}
