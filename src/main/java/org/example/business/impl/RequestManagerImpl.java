package org.example.business.impl;

import lombok.AllArgsConstructor;
import org.example.business.RequestManager;
import org.example.domain.Property;
import org.example.domain.Request;
import org.example.domain.RequestStatus;
import org.example.domain.User;
import org.example.persistence.RequestRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class RequestManagerImpl implements RequestManager {
    private final RequestRepository requestRepository;

    @Override
    public Long createRequest(long homeownerId, long propertyId) {
            return requestRepository.createRequest(setParams(homeownerId, propertyId));
    }

    private Request setParams(long homeownerId, long propertyId) {
        Request request = new Request();
        request.setRequestStatus(RequestStatus.PENDING);
        request.setDate(LocalDateTime.now());
        request.setHomeowner(new User());
        request.setProperty(new Property());
        request.getHomeowner().setId(homeownerId);
        request.getProperty().setId(propertyId);
        return request;
    }

    @Override
    public List<Request> getRequests(RequestStatus status) {
        return requestRepository.getRequests(status);
    }
}
