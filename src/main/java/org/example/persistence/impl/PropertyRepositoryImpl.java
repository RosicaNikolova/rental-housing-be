package org.example.persistence.impl;

import lombok.AllArgsConstructor;
import org.example.domain.Property;
import org.example.persistence.JPAPropertyRepository;
import org.example.persistence.PropertyRepository;
import org.example.persistence.converters.*;


import org.example.persistence.entity.PropertyEntity;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class PropertyRepositoryImpl implements PropertyRepository {

    private JPAPropertyRepository jpaPropertyRepository;

    //   private static long NEXT_ID = 1;
//    private final List<PropertyEntity> savedProperties;
//
//    public FakePropertyRepositoryImpl() {
//
//        this.savedProperties = new ArrayList<>();
//        PropertyEntity propertyEntity = new PropertyEntity();
//        propertyEntity.setId(NEXT_ID);
//        propertyEntity.setStreet("Pisano");
//        propertyEntity.setPropertyType(PropertyType.APARTMENT);
//        PropertyEntity propertyEntity1 = new PropertyEntity();
//        propertyEntity1.setId(NEXT_ID + 1);
//        propertyEntity1.setStreet("Europalaan");
//        propertyEntity1.setPropertyType(PropertyType.HOUSE);
//        savedProperties.add(propertyEntity);
//        savedProperties.add(propertyEntity1);
//
//    }


    @Override
    public List<Property> findAll() {
        //Getting entities and converting them to domain objects
        List<Property> properties = jpaPropertyRepository.findAll().stream().map(PropertyConverter::convert).toList();;
        //List<Property> propertiesConverted = savedProperties.stream().map(PropertyConverter::convert).toList();
        return properties;
    }



    @Override
    public Optional<Property> findById(long propertyId) {

        Optional<Property> property = jpaPropertyRepository.findById(propertyId).map(PropertyConverter::convert);
        return property;
    }

    @Override
    public Property createProperty(Property request) {

        ModelMapper modelMapper = new ModelMapper();
        PropertyEntity propertyEntity = modelMapper.map(request, PropertyEntity.class);
        jpaPropertyRepository.save(propertyEntity);
        Property property = modelMapper.map(propertyEntity, Property.class);
        return property;

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
