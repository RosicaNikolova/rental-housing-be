package org.example.controller;
import java.util.List;
import java.util.Properties;

import lombok.AllArgsConstructor;
import org.example.business.PropertyManager;
import org.example.business.impl.PropertyManagerImpl;
import org.example.domain.GetAllPropertiesResponse;
import org.example.domain.Property;
import org.example.domain.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/properties")
@AllArgsConstructor
public class PropertiesController {
    private final PropertyManagerImpl propertyManager;

    @GetMapping("property")
    public ResponseEntity<Property> getProperty(){
        final Property property = propertyManager.getProperty();
        return ResponseEntity.ok().body(property);
    }

    @GetMapping
    public ResponseEntity<GetAllPropertiesResponse> getProperties(){
        GetAllPropertiesResponse response = propertyManager.getProperties();
        return  ResponseEntity.ok(response);
    }
    @GetMapping("propertyTest")
    public ResponseEntity<Property> getPropertyTest(){
        final Property property = propertyManager.getProperty();
        return ResponseEntity.ok().body(property);
    }

}
