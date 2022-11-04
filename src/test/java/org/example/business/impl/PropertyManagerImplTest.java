package org.example.business.impl;

import org.example.business.Exceptions.CreatePropertyException;
import org.example.business.Exceptions.DeletePropertyException;
import org.example.business.Exceptions.UpdatePropertyException;
import org.example.domain.Property;
import org.example.domain.PropertyType;
import org.example.persistence.PropertyRepository;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
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

    @Test
    void CreatePropertySuccessful(){
        PropertyRepository propertyRepositoryMock = mock(PropertyRepository.class);

        Property property = Property.builder()
                .propertyType(PropertyType.APARTMENT)
                .city("Eindhoven")
                .postCode("AAAA")
                .price(100)
                .build();

        when(propertyRepositoryMock.createProperty(property))
               .thenReturn(1L);

        PropertyManagerImpl propertyManager = new PropertyManagerImpl(propertyRepositoryMock);

        Long actualId = propertyManager.createProperty(property);
        assertEquals(1L, actualId);
        verify(propertyRepositoryMock).createProperty(property);
    }

    @Test()
    void CreatePropertyThrowsAnException(){
        PropertyRepository propertyRepositoryMock = mock(PropertyRepository.class);

        Property property = Property.builder()
                .propertyType(PropertyType.APARTMENT)
                .city("Eindhoven")
                .postCode("AAAA")
                .price(100)
                .build();

        when(propertyRepositoryMock.createProperty(property))
                .thenThrow(new CreatePropertyException("exception"));

        PropertyManagerImpl propertyManager = new PropertyManagerImpl(propertyRepositoryMock);

        assertThrows(CreatePropertyException.class, () -> propertyManager.createProperty(property));
        verify(propertyRepositoryMock).createProperty(property);

    }

    @Test()
    void UpdatePropertySuccessful(){
        PropertyRepository propertyRepositoryMock = mock(PropertyRepository.class);

        Property property = Property.builder()
                .propertyType(PropertyType.APARTMENT)
                .city("Eindhoven")
                .postCode("AAAA")
                .price(100)
                .build();

        PropertyManagerImpl propertyManager = new PropertyManagerImpl(propertyRepositoryMock);

        propertyManager.updateProperty(property);
        verify(propertyRepositoryMock).updateProperty(property);

    }

    @Test()
    void UpdatePropertyThrowsAnException(){
        PropertyRepository propertyRepositoryMock = mock(PropertyRepository.class);

        Property property = Property.builder()
                .propertyType(PropertyType.APARTMENT)
                .city("Eindhoven")
                .postCode("AAAA")
                .price(100)
                .build();

        doThrow(new UpdatePropertyException("exception")).when(propertyRepositoryMock).updateProperty(property);

//        when(propertyRepositoryMock.updateProperty(property))
//                .thenThrow(new CreatePropertyException("exception"));

        PropertyManagerImpl propertyManager = new PropertyManagerImpl(propertyRepositoryMock);

        assertThrows(UpdatePropertyException.class, () -> propertyManager.updateProperty(property));
        verify(propertyRepositoryMock).updateProperty(property);

    }


    @Test()
    void DeletePropertySuccessful(){
        PropertyRepository propertyRepositoryMock = mock(PropertyRepository.class);

        PropertyManagerImpl propertyManager = new PropertyManagerImpl(propertyRepositoryMock);

        propertyManager.deleteProperty(1L);
        verify(propertyRepositoryMock).deleteProperty(1L);

    }


    @Test()
    void DeletePropertyThrowsAnException(){
        PropertyRepository propertyRepositoryMock = mock(PropertyRepository.class);


        doThrow(new DeletePropertyException("exception")).when(propertyRepositoryMock).deleteProperty(1L);

//        when(propertyRepositoryMock.updateProperty(property))
//                .thenThrow(new CreatePropertyException("exception"));

        PropertyManagerImpl propertyManager = new PropertyManagerImpl(propertyRepositoryMock);

        assertThrows(DeletePropertyException.class, () -> propertyManager.deleteProperty(1L));
        verify(propertyRepositoryMock).deleteProperty(1L);

    }

}