package org.example.persistence;

import org.example.domain.Request;
import org.example.domain.RequestStatus;

import java.util.List;

public interface RequestRepository {

    Long createRequest(Request request);

    List<Request> getRequests(RequestStatus status);

    List<Request> getRequestsForHomeowner(Long id);
}
