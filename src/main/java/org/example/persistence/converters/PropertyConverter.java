package org.example.persistence.converters;


import org.example.domain.Property;
import org.example.persistence.entity.PropertyEntity;

//@NoArgsConstructor
public final class PropertyConverter {


    private PropertyConverter(){

    }
    public static Property convert(PropertyEntity property) {
        return Property.builder()
                .id(property.getId())
                .price(property.getPrice())
                .propertyType(property.getPropertyType())
                .city(property.getCity())
                .street(property.getStreet())
                .postCode(property.getPostcode())
                .streetNumber(property.getStreetNumber())
                .livingSpace(property.getLivingSpace())
                .numberOfRooms(property.getNumberOfRooms())
                .numberOfBedrooms(property.getNumberOfBedrooms())
                .propertyStatus(property.getPropertyStatus())
                .build();
    }

    public static PropertyEntity convertToEntity(Property property){
        return PropertyEntity.builder()
                .price(property.getPrice())
                .propertyType(property.getPropertyType())
                .city(property.getCity())
                .street(property.getStreet())
                .streetNumber(property.getStreetNumber())
                .livingSpace(property.getLivingSpace())
                .numberOfRooms(property.getNumberOfRooms())
                .numberOfBedrooms(property.getNumberOfBedrooms())
                .propertyStatus(property.getPropertyStatus())
                .build();
    }
}
