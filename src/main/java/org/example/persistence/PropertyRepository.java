package org.example.persistence;

import org.example.persistence.entity.PropertyEntity;

import java.util.List;
import java.util.Optional;

public interface PropertyRepository {
    List<PropertyEntity> findAll();
    Optional<PropertyEntity> findById(long propertyId);
}
