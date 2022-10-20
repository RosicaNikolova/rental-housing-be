package org.example.business;
import java.util.List;
import java.util.Optional;

import org.example.controller.DTO.PropertyDTO;
import org.example.domain.Property;
import org.example.domain.Responses.GetAllPropertiesResponse;


public interface PropertyManager {
    Optional<Property> getProperty(long id);
    List<Property> getProperties();
    Property createProperty(Property request);
}
