package org.example.controller.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.domain.PropertyStatus;
import org.example.domain.PropertyType;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class PropertyDTO {

    //private Long id;

    @NotNull
    private double price;

    @NotNull
    private PropertyType propertyType;

    @NotNull
    private String city;

    @NotBlank
    private String street;

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
