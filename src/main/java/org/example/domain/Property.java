package org.example.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Property {
    private int id;
    private double price;
    private PropertyType propertyType;
    private String city;
    private String street;
    private int streetNumber;
    private int livingSpace;
    private int numberOfRooms;
    private int numberOfBedrooms;
}
