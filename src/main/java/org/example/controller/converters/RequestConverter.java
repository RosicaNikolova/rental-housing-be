package org.example.controller.converters;


import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.example.controller.DTO.PropertyDTO;
import org.example.controller.DTO.RequestDTO;
import org.example.domain.Property;
import org.example.domain.Request;
import org.example.domain.User;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class RequestConverter {
    ModelMapper modelMapper;

//    public Request convertToRequest(RequestDTO requestDTO){
//
////        Request request = Request.builder()
////                .homeowner(new User())
////                .build();
////        request.setProperty(modelMapper.map(requestDTO.getProperty(), Property.class));
////        request.getHomeowner().setId(requestDTO.getHomeownerId());
////        return  request;
//    }


    public RequestDTO convertToDTO(Request request){
                return RequestDTO.builder()
                .property(modelMapper.map(request.getProperty(), PropertyDTO.class ))
                .id(request.getId())
                .homeownerFirstName(request.getHomeowner().getFirstName())
                .homeownerLastName(request.getHomeowner().getLastName())
                .status(request.getRequestStatus())
                .date(request.getDate())
                .build();

    }

}
