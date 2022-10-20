package org.example.domain;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Property {

    private Long id;

    private double price;

    private PropertyType propertyType;

    private String city;

    private String street;

    private String postCode;

    private int streetNumber;

    private int livingSpace;

    private int numberOfRooms;

    private int numberOfBedrooms;

    private PropertyStatus propertyStatus;
}
