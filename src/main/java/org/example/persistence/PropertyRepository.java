package org.example.persistence;


import org.example.domain.Property;


import java.util.List;
import java.util.Optional;


public interface PropertyRepository {
    List<Property> findAll();

    Optional<Property> findById(long propertyId);

    Long createProperty(Property request);

    boolean findByPostCodeAndPrice(String postCode, double price);
}
