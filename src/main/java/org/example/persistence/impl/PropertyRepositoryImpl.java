package org.example.persistence.impl;

import lombok.AllArgsConstructor;
import org.example.business.Exceptions.CreatePropertyException;
import org.example.business.Exceptions.DeletePropertyException;
import org.example.business.Exceptions.UpdatePropertyException;
import org.example.domain.Property;
import org.example.domain.PropertyStatus;
import org.example.persistence.JPAPropertyRepository;
import org.example.persistence.PropertyRepository;
import org.example.persistence.converters.*;


import org.example.persistence.entity.PropertyEntity;
import org.example.persistence.entity.UserEntity;
import org.modelmapper.ModelMapper;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class PropertyRepositoryImpl implements PropertyRepository {

    private final JPAPropertyRepository jpaPropertyRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<Property> findAll() {
        //Getting entities and converting them to domain objects
        return jpaPropertyRepository.findAllByPropertyStatus(PropertyStatus.ACTIVE).stream().map(PropertyConverter::convert).toList();
    }


    @Override
    public Optional<Property> findById(long propertyId) {
        return jpaPropertyRepository.findById(propertyId).map(PropertyConverter::convert);
    }

    @Override
    public Long createProperty(Property request) {

        PropertyEntity propertyEntity = modelMapper.map(request, PropertyEntity.class);
        propertyEntity.setUserEntity(UserEntity.builder().id(request.getIdHomeowner()).build());

        //Catch the SQL exception
        //Rethrows custom exception
        try {
            return jpaPropertyRepository.save(propertyEntity).getId();
        }
        catch (Exception e){
            throw new CreatePropertyException("Error while creating property");
        }
    }


    @Override
    public void updateProperty(Property property) {
        PropertyEntity propertyEntity = modelMapper.map(property, PropertyEntity.class);
        try {
            jpaPropertyRepository.save(propertyEntity);
        }
        catch (Exception e){
            throw new UpdatePropertyException("Error while updating property");
        }
    }

    @Override
    public void deleteProperty(long propertyId) {
        try {
            jpaPropertyRepository.deleteById(propertyId);
        }
        catch (Exception e){
            throw new DeletePropertyException("Error while deleting the property");
        }
    }
}
