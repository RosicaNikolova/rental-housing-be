package org.example.persistence.impl;

import lombok.AllArgsConstructor;
import org.example.domain.Property;
import org.example.persistence.JPAPropertyRepository;
import org.example.persistence.PropertyRepository;
import org.example.persistence.converters.*;


import org.example.persistence.entity.PropertyEntity;
import org.modelmapper.ModelMapper;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class PropertyRepositoryImpl implements PropertyRepository {

    private JPAPropertyRepository jpaPropertyRepository;


    @Override
    public List<Property> findAll() {
        //Getting entities and converting them to domain objects

        return jpaPropertyRepository.findAll().stream().map(PropertyConverter::convert).toList();
    }



    @Override
    public Optional<Property> findById(long propertyId) {
        return jpaPropertyRepository.findById(propertyId).map(PropertyConverter::convert);
    }

    @Override
    public Long createProperty(Property request) {

        ModelMapper modelMapper = new ModelMapper();
        PropertyEntity propertyEntity = modelMapper.map(request, PropertyEntity.class);

        return jpaPropertyRepository.save(propertyEntity).getId();

    }

    @Override
    public boolean findByPostCodeAndPrice(String postCode, double price) {
        List<PropertyEntity> list= jpaPropertyRepository.findFirstByPostcodeAndPrice(postCode, price);
        if(list.isEmpty()){
            return true;
        }
        else{
            return false;
        }
    }
}
