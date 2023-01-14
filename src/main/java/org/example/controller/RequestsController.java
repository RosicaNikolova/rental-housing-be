package org.example.controller;

import lombok.AllArgsConstructor;
import org.example.business.Exceptions.CreatePropertyException;
import org.example.business.Exceptions.DeletePropertyException;
import org.example.business.PropertyManager;
import org.example.business.RequestManager;
import org.example.controller.DTO.CreatePropertyResponse;
import org.example.controller.DTO.GetRequestsResponse;
import org.example.controller.DTO.PropertyDTO;
import org.example.controller.DTO.RequestDTO;
import org.example.controller.converters.RequestConverter;
import org.example.domain.Property;
import org.example.domain.Request;
import org.example.domain.RequestStatus;
import org.example.security.isauthenticated.IsAuthenticated;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Role;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping(path = "requests")
@AllArgsConstructor
public class RequestsController {

    PropertyManager propertyManager;
    RequestManager requestManager;
    ModelMapper modelMapper;
    RequestConverter requestConverter;

    @IsAuthenticated
    @RolesAllowed("ROLE_HOMEOWNER")
    @PostMapping()
    public ResponseEntity<CreatePropertyResponse> createRequest(@RequestBody @Valid PropertyDTO createdRequest) {

        //Request request = requestConverter.convertToRequest(createdRequest);
        Property propertyRequest = modelMapper.map(createdRequest, Property.class);
        Long propertyId = propertyManager.createProperty(propertyRequest);

        Long requestId = requestManager.createRequest(propertyRequest.getIdHomeowner(), propertyId);

        //DTO for creating properties, cloud be changed to requestDTO
        CreatePropertyResponse createPropertyResponse = new CreatePropertyResponse();
        try{
            createPropertyResponse.setId(requestId);
        }
        catch(CreatePropertyException e){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(createPropertyResponse);
    }

    @IsAuthenticated
    @RolesAllowed("ROLE_ADMIN")
    @GetMapping
    public ResponseEntity<GetRequestsResponse> getRequests(@RequestParam(value = "status") final RequestStatus status){

        List<Request> requests = requestManager.getRequests(status);
        if (requests.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        else{
            List<RequestDTO> dtos = requests.stream().map(request -> requestConverter.convertToDTO(request)).toList();
            GetRequestsResponse response = new GetRequestsResponse(dtos);

            return ResponseEntity.ok(response);
        }
    }

    @IsAuthenticated
    @RolesAllowed("ROLE_HOMEOWNER")
    @GetMapping("/homeowner")
    public ResponseEntity<GetRequestsResponse> getRequestsForHomeowner(){

        List<Request> requestsForHomeowner = requestManager.getRequestsForHomeowner();
        if (requestsForHomeowner.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        else{
            List<RequestDTO> dtos = requestsForHomeowner.stream().map(request -> requestConverter.convertToDTO(request)).toList();
            GetRequestsResponse response = new GetRequestsResponse(dtos);
            return ResponseEntity.ok(response);
        }
    }



}
