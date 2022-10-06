package org.example.business;
import java.util.Optional;

import org.example.controller.DTO.PropertyDTO;
import org.example.domain.Responses.GetAllPropertiesResponse;


public interface PropertyManager {
    public Optional<PropertyDTO> getProperty(long id);
    public GetAllPropertiesResponse getProperties();
}
