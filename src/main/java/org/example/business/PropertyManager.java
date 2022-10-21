package org.example.business;
import java.util.List;
import java.util.Optional;

import org.example.domain.Property;



public interface PropertyManager {
    Optional<Property> getProperty(long id);
    List<Property> getProperties();
    Property createProperty(Property request);
}
