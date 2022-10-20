package org.example.persistence;

import lombok.NoArgsConstructor;
import org.example.domain.Property;
import org.example.persistence.entity.PropertyEntity;

import java.util.List;
import java.util.Optional;


public interface PropertyRepository {
    List<Property> findAll();

    Optional<Property> findById(long propertyId);

    Property createProperty(Property request);

    boolean findByPostCodeAndPrice(String postCode, double price);
}
