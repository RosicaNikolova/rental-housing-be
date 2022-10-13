package org.example.persistence.converters;

import lombok.NoArgsConstructor;
import org.example.domain.Property;
import org.example.persistence.entity.PropertyEntity;

@NoArgsConstructor
public final class PropertyConverter {


    public static Property convert(PropertyEntity property) {
        return Property.builder()
                .id(property.getId())
                .propertyType(property.getPropertyType())
                .street(property.getStreet())
                .build();
    }
}
