package org.example.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Property {
    private long id;
    private double price;
    private PropertyType propertyType;
    private String city;
    private String street;
    private int streetNumber;
    private int livingSpace;
    private int numberOfRooms;
    private int numberOfBedrooms;
}
