package org.example.business.impl;

import org.example.domain.Property;
import org.example.domain.PropertyType;
import org.example.persistence.PropertyRepository;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class PropertyManagerImplTest {

    @Test
    void getPropertiesReturnsAllProperties(){
        PropertyRepository propertyRepositoryMock = mock(PropertyRepository.class);
        Property property1 = Property.builder()
                .id(1L)
                .propertyType(PropertyType.APARTMENT)
                .city("Eindhoven")
                .build();
        Property property2 = Property.builder()
                .id(2L)
                .propertyType(PropertyType.HOUSE)
                .city("Eindhoven")
                .build();

        List<Property> expectedResult = List.of(property1,property2);

        when(propertyRepositoryMock.findAll())
                .thenReturn(expectedResult);

        PropertyManagerImpl propertyManager = new PropertyManagerImpl(propertyRepositoryMock);
        List<Property> actualResult = propertyManager.getProperties();
        assertEquals(expectedResult, actualResult);
        verify(propertyRepositoryMock).findAll();

    }

    @Test
    void getPropertiesReturnsEmptyList(){
        PropertyRepository propertyRepositoryMock = mock(PropertyRepository.class);

        List<Property> expectedResult = new ArrayList<>();

        when(propertyRepositoryMock.findAll())
                .thenReturn(expectedResult);

        PropertyManagerImpl propertyManager = new PropertyManagerImpl(propertyRepositoryMock);
        List<Property> actualResult = propertyManager.getProperties();
        assertEquals(expectedResult, actualResult);
        verify(propertyRepositoryMock).findAll();

    }


    @Test
    void getPropertyByIdFound(){
        PropertyRepository propertyRepositoryMock = mock(PropertyRepository.class);

        Property property1 = Property.builder()
                .id(1L)
                .propertyType(PropertyType.APARTMENT)
                .city("Eindhoven")
                .build();

        Optional<Property> expectedResult = Optional.of(property1);

        when(propertyRepositoryMock.findById(1L))
                .thenReturn(expectedResult);

        PropertyManagerImpl propertyManager = new PropertyManagerImpl(propertyRepositoryMock);
        Optional<Property> actualResult= propertyManager.getProperty(1L);
        assertEquals(expectedResult, actualResult);
        verify(propertyRepositoryMock).findById(1L);

    }

    @Test
    void getPropertyByIdNotFound(){
        PropertyRepository propertyRepositoryMock = mock(PropertyRepository.class);

        Property property1 = Property.builder()
                .id(1L)
                .propertyType(PropertyType.APARTMENT)
                .city("Eindhoven")
                .build();

        Optional<Property> propertyOptional = Optional.of(property1);

        when(propertyRepositoryMock.findById(1L))
                .thenReturn(propertyOptional);

        PropertyManagerImpl propertyManager = new PropertyManagerImpl(propertyRepositoryMock);
        Optional<Property> actualResult= propertyManager.getProperty(2L);
        assertEquals(Optional.empty(), actualResult);
        verify(propertyRepositoryMock).findById(2L);

    }


}