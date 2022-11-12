package org.example.controller.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.domain.PropertyStatus;
import org.example.domain.PropertyType;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class PropertyDTO {

    private Long id;

    @NotNull
    private String city;

    @NotBlank
    private String street;

    @NotNull
    private int streetNumber;

    @NotBlank
    private String postCode;

    @NotNull
    private PropertyType propertyType;

    @NotNull
    private int livingSpace;

    @NotNull
    private int numberOfRooms;

    @NotNull
    private int numberOfBedrooms;

    @NotNull
    private double price;

    @NotNull
    private PropertyStatus propertyStatus;

    private Long idHomeowner;
}
