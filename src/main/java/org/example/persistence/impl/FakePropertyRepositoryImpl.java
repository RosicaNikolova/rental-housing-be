package org.example.persistence.impl;

import org.example.domain.Property;
import org.example.domain.PropertyType;
import org.example.persistence.PropertyRepository;
import org.example.persistence.entity.PropertyEntity;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Repository
public class FakePropertyRepositoryImpl implements PropertyRepository {


    private static long NEXT_ID = 1;
    private final List<PropertyEntity> savedProperties;

    public FakePropertyRepositoryImpl() {

        this.savedProperties = new ArrayList<>();
        PropertyEntity propertyEntity = new PropertyEntity();
        propertyEntity.setId(NEXT_ID);
        propertyEntity.setStreet("Pisano");
        propertyEntity.setPropertyType(PropertyType.APARTMENT);
        PropertyEntity propertyEntity1 = new PropertyEntity();
        propertyEntity1.setId(NEXT_ID + 1);
        savedProperties.add(propertyEntity);
        savedProperties.add(propertyEntity1);
    }



    @Override
    public List<PropertyEntity> findAll() {

        return savedProperties;
    }

    @Override
    public Optional<PropertyEntity> findById(long propertyId) {
        return this.savedProperties.stream()
                .filter(propertyEntity -> propertyEntity.getId().equals(propertyId))
                .findFirst();
    }
}
