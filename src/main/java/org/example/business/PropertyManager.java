package org.example.business;
import java.util.List;
import java.util.Optional;

import org.example.business.Exceptions.CreatePropertyException;
import org.example.domain.Property;



public interface PropertyManager {
    Optional<Property> getProperty(long id);
    List<Property> getProperties();
    Long createProperty(Property request);

    void updateProperty(Property property);

    void deleteProperty(long propertyId);
}
