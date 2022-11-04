package org.example.controller.DTO;

import lombok.Data;
import org.example.domain.PropertyStatus;
import org.example.domain.PropertyType;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class UpdatePropertyRequest {

    private Long id;

    @NotNull
    private double price;

    @NotNull
    private PropertyType propertyType;

    @NotNull
    private String city;

    @NotBlank
    private String street;

    @NotBlank
    private String postCode;

    @NotNull
    private int streetNumber;

    @NotNull
    private int livingSpace;

    @NotNull
    private int numberOfRooms;

    @NotNull
    private int numberOfBedrooms;

    @NotNull
    private PropertyStatus propertyStatus;
}
