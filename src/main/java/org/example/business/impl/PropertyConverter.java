package org.example.business.impl;

import org.example.domain.Property;
import org.example.persistence.entity.PropertyEntity;

final class PropertyConverter {

    private PropertyConverter(){
    }

    public static Property convert(PropertyEntity property) {
        return Property.builder()
                .id(property.getId())
                .build();
    }
}
