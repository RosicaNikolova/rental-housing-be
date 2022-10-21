package org.example.controller;

import java.util.List;
import java.util.Optional;

import lombok.AllArgsConstructor;
import org.example.business.impl.PropertyManagerImpl;
import org.example.controller.DTO.PropertyDTO;
import org.example.domain.Property;
import org.example.domain.Responses.GetAllPropertiesResponse;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping(path = "properties")
@AllArgsConstructor
public class PropertiesController {
    private final PropertyManagerImpl propertyManager;
    private ModelMapper modelMapper;


    @GetMapping("{id}")
    public ResponseEntity<PropertyDTO> getProperty(@PathVariable(value = "id") final long id){

        final Optional<Property> propertyOptional = propertyManager.getProperty(id);

        if(propertyOptional.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        else{
            Optional<PropertyDTO> property = Optional.of(modelMapper.map(propertyOptional.get(), PropertyDTO.class));
            return ResponseEntity.ok().body(property.get());
        }
    }

    @GetMapping
    public ResponseEntity<GetAllPropertiesResponse> getProperties(){

        List <Property> properties= propertyManager.getProperties();
        if (properties.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        else{
            GetAllPropertiesResponse response = new GetAllPropertiesResponse(properties
                    .stream()
                    .map(property -> modelMapper.map(property, PropertyDTO.class))
                    .toList());
            return ResponseEntity.ok(response);
        }

    }

    @PostMapping()
    public ResponseEntity<PropertyDTO> createProperty(@RequestBody @Valid PropertyDTO request) {

        Property propertyRequestConverted = modelMapper.map(request, Property.class);
        Property propertyCreated = propertyManager.createProperty(propertyRequestConverted);
        if(propertyCreated != null) {
            PropertyDTO response = modelMapper.map(propertyCreated, PropertyDTO.class);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        }
        else{
            return ResponseEntity.badRequest().build();
        }
    }

}
