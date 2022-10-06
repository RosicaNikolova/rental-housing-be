package org.example.domain;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Property {

    @Getter
    private long id;

    @Getter
    private double price;

    @Getter
    private PropertyType propertyType;

    @Getter
    private String city;

    @Getter
    private String street;

    @Getter
    private int streetNumber;

    @Getter
    private int livingSpace;

    @Getter
    private int numberOfRooms;

    @Getter
    private int numberOfBedrooms;
}
