package org.example.persistence.impl;

import org.example.persistence.PropertyRepository;
import org.example.persistence.entity.PropertyEntity;
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
    }



    @Override
    public List<PropertyEntity> findAll() {
        return Collections.unmodifiableList(this.savedProperties);
    }

    @Override
    public Optional<PropertyEntity> findById(long propertyId) {
        return this.savedProperties.stream()
                .filter(propertyEntity -> propertyEntity.getId().equals(propertyId))
                .findFirst();
    }
}
