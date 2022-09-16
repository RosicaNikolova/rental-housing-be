package org.example.business;
import java.util.List;

import org.example.domain.GetAllPropertiesResponse;
import org.example.domain.Property;

public interface PropertyManager {
    public Property getProperty();
    public GetAllPropertiesResponse getProperties();
}
