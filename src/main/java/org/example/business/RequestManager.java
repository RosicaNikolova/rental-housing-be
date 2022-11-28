package org.example.business;

import org.example.domain.Request;
import org.example.domain.RequestStatus;

import java.util.List;

public interface RequestManager {

    Long createRequest(long homeownerId, long propertyId);

    List<Request> getRequests(RequestStatus status);

    List<Request> getRequestsForHomeowner(Long id);
}
