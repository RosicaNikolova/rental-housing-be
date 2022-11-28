package org.example.persistence.impl;

import lombok.AllArgsConstructor;
import org.example.domain.Request;
import org.example.domain.RequestStatus;
import org.example.persistence.JPARequestsRepository;
import org.example.persistence.RequestRepository;
import org.example.persistence.converters.RequestEntityConverter;
import org.example.persistence.entity.RequestEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@AllArgsConstructor
public class RequestRepositoryImpl implements RequestRepository {

    private final JPARequestsRepository jpaRequestsRepository;
    private final RequestEntityConverter requestEntityConverter;

    @Override
    public Long createRequest(Request request) {

        RequestEntity requestEntity = requestEntityConverter.convert(request);
        return jpaRequestsRepository.save(requestEntity).getId();
    }

    @Override
    public List<Request> getRequests(RequestStatus status) {

        List<RequestEntity> entities = jpaRequestsRepository.findRequestEntitiesByRequestStatus(status);
        return  entities
                .stream()
                .map(entity -> requestEntityConverter.convertToRequest(entity))
                .toList();
    }

    @Override
    public List<Request> getRequestsForHomeowner(Long id) {
        List<RequestEntity> entities = jpaRequestsRepository.findRequestEntitiesByUserEntity_Id(id);
        return entities
                .stream()
                .map(entity -> requestEntityConverter.convertToRequest(entity))
                .toList();
    }
}
