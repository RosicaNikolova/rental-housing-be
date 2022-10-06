package org.example.persistence.entity;

import lombok.Getter;
import lombok.Setter;
import org.example.domain.PropertyType;


public class PropertyEntity {

    @Getter
    private Long id;

    @Getter
    @Setter
    private double price;

    @Getter
    @Setter
    private PropertyType propertyType;

    @Getter
    @Setter
    private String city;

    @Getter
    @Setter
    private String street;

    @Getter
    @Setter
    private int streetNumber;

    @Getter
    @Setter
    private int livingSpace;

    @Getter
    @Setter
    private int numberOfRooms;

    @Getter
    @Setter
    private int numberOfBedrooms;

}
