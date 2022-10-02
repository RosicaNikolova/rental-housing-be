package org.example.business;
import java.util.List;
import java.util.Optional;

import org.example.domain.GetAllPropertiesResponse;
import org.example.domain.Property;



public interface PropertyManager {
    public Optional<Property> getProperty(long id);
    public GetAllPropertiesResponse getProperties();
}
