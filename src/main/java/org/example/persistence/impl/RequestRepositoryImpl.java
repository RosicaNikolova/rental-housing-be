package org.example.persistence.impl;

import lombok.AllArgsConstructor;
import org.example.domain.Request;
import org.example.domain.RequestStatus;
import org.example.persistence.JPARequestsRepository;
import org.example.persistence.RequestRepository;
import org.example.persistence.converters.RequestEntityConverter;
import org.example.persistence.entity.RequestEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Repository;

import java.io.Console;
import java.util.ArrayList;
import java.util.List;

@Repository
@AllArgsConstructor
public class RequestRepositoryImpl implements RequestRepository {

    private JPARequestsRepository jpaRequestsRepository;
    private RequestEntityConverter requestEntityConverter;

    @Override
    public Long createRequest(Request request) {

        RequestEntity requestEntity = requestEntityConverter.convert(request);
        return jpaRequestsRepository.save(requestEntity).getId();
    }

    @Override
    public List<Request> getRequests(RequestStatus status) {

        List<RequestEntity> entities = jpaRequestsRepository.getRequests();
        return  entities
                .stream()
                .map(entity -> requestEntityConverter.convertToRequest(entity))
                .toList();
    }
}