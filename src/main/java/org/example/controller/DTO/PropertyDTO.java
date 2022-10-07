package org.example.controller.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.domain.PropertyType;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class PropertyDTO {

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
