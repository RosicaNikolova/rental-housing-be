package org.example.controller;

import java.util.List;
import java.util.Optional;

import lombok.AllArgsConstructor;
import org.example.business.Exceptions.CreatePropertyException;
import org.example.business.Exceptions.DeletePropertyException;
import org.example.business.Exceptions.UpdatePropertyException;
import org.example.business.impl.PropertyManagerImpl;
import org.example.controller.DTO.PropertyDTO;
import org.example.controller.DTO.UpdatePropertyRequest;
import org.example.domain.Property;
import org.example.controller.DTO.CreatePropertyResponse;
import org.example.controller.DTO.GetAllPropertiesResponse;
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
    public ResponseEntity<PropertyDTO> getProperty(@PathVariable(value = "id") final Long id){

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
    public ResponseEntity<CreatePropertyResponse> createProperty(@RequestBody @Valid PropertyDTO request) {

        Property propertyRequestConverted = modelMapper.map(request, Property.class);

        CreatePropertyResponse createPropertyResponse = new CreatePropertyResponse();
        try{
        createPropertyResponse.setId(propertyManager.createProperty(propertyRequestConverted));
        }
        catch(CreatePropertyException e){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(createPropertyResponse);
    }

    @PutMapping("{id}")
    public ResponseEntity<Void> updateProperty(@PathVariable("id") final Long id,
                                              @RequestBody @Valid UpdatePropertyRequest request) {
        request.setId(id);

        Property property = modelMapper.map(request, Property.class);
        try {
            propertyManager.updateProperty(property);
        }
        catch (UpdatePropertyException e){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }
    @DeleteMapping("{propertyId}")
    public ResponseEntity<Void> deleteProperty(@PathVariable final Long propertyId) {
        try {
            propertyManager.deleteProperty(propertyId);
        }
        catch (DeletePropertyException e){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }
}
