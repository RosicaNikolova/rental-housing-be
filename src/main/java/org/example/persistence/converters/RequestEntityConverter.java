package org.example.persistence.converters;

import lombok.AllArgsConstructor;
import org.example.domain.Property;
import org.example.domain.Request;
import org.example.domain.User;
import org.example.persistence.entity.PropertyEntity;
import org.example.persistence.entity.RequestEntity;
import org.example.persistence.entity.UserEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RequestEntityConverter {

    ModelMapper modelMapper;
    public RequestEntity convert(Request request) {
        RequestEntity entity = RequestEntity.builder()
                .id(request.getId())
                .date(request.getDate())
                .requestStatus(request.getRequestStatus())
                .propertyEntity(new PropertyEntity())
                .userEntity(new UserEntity())
               // .(request.getHomeowner().getId())
               // .propertyId(request.getProperty().getId())
                .reasonRejection(request.getReasonRejection())
                .build();

        entity.getPropertyEntity().setId(request.getProperty().getId());
        entity.getUserEntity().setId(request.getHomeowner().getId());
        return entity;
    }

    public Request convertToRequest(RequestEntity entity) {
        return Request.builder()
                .property(modelMapper.map(entity.getPropertyEntity(),Property.class))
                .homeowner(modelMapper.map(entity.getUserEntity(), User.class))
                .date(entity.getDate())
                .id(entity.getId())
                .requestStatus(entity.getRequestStatus())
                .build();
    }
}
