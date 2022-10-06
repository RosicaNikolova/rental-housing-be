package org.example.controller;
import java.util.Optional;

import lombok.AllArgsConstructor;
import org.example.business.impl.PropertyManagerImpl;
import org.example.controller.DTO.PropertyDTO;
import org.example.domain.Responses.GetAllPropertiesResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "properties/")
@AllArgsConstructor
public class PropertiesController {
    private final PropertyManagerImpl propertyManager;

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
        GetAllPropertiesResponse response = propertyManager.getProperties();
        return ResponseEntity.ok(response);
    }

    /*@GetMapping("propertyTest")
    public ResponseEntity<Property> getPropertyTest(){
        final Property property = propertyManager.getProperty();
        return ResponseEntity.ok().body(property);
    } */

}
