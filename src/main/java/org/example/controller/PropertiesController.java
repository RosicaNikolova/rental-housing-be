package org.example.controller;
import java.util.List;
import java.util.Optional;

import lombok.AllArgsConstructor;
import org.example.business.impl.PropertyManagerImpl;
import org.example.controller.DTO.PropertyDTO;
import org.example.domain.Property;
import org.example.domain.Responses.GetAllPropertiesResponse;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping(path = "properties")
@AllArgsConstructor
public class PropertiesController {
    private final PropertyManagerImpl propertyManager;
    private ModelMapper modelMapper;


    @GetMapping("{id}")
    public ResponseEntity<PropertyDTO> getProperty(@PathVariable(value = "id") final long id){

        final Optional<PropertyDTO> propertyOptional = propertyManager.getProperty(id);
        if(propertyOptional.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        else{
            return ResponseEntity.ok().body(propertyOptional.get());
        }
    }

    @GetMapping
    public ResponseEntity<GetAllPropertiesResponse> getProperties(){

        //Calling business layer
        List<Property> properties = propertyManager.getProperties();
        List<PropertyDTO> dtos = properties.stream().map(property -> modelMapper.map(property, PropertyDTO.class)).toList();
        GetAllPropertiesResponse response = new GetAllPropertiesResponse(dtos);
        return ResponseEntity.ok(response);
    }

    /*@GetMapping("propertyTest")
    public ResponseEntity<Property> getPropertyTest(){
        final Property property = propertyManager.getProperty();
        return ResponseEntity.ok().body(property);
    } */

}
