package org.example.business.impl.IntegrationTests;

import org.example.business.RequestManager;
import org.example.domain.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class RequestsControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private RequestManager requestManager;


    //Testing if returns all pending inquiries, only admin should have access to this resource
    @Test
    @WithMockUser(username = "rosica@mail.com", roles = {"ADMIN"})
    void getRequests_shouldReturn200WithRequests_whenFound() throws Exception {
        Request request = CreatePendingRequest();

        List<Request> requestList = new ArrayList<>();
        requestList.add(request);
        when(requestManager.getRequests(RequestStatus.PENDING)).thenReturn(requestList);

        mockMvc.perform(get("/requests?status=PENDING"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Type", APPLICATION_JSON_VALUE))
                .andExpect(content().json("""
                            {
                            "requestDTOList":
                            [
                                {"id": 2, "status": "PENDING","date": "2022-11-11T20:00:00","homeownerFirstName": "Rositsa","homeownerLastName": "Nikolova","reasonRejection": null,
                                         "property": {
                                                         "id": 3,
                                                         "city": "Amsterdam",
                                                         "street": "Pisano",
                                                         "streetNumber": 2,
                                                         "postCode": "1000",
                                                         "propertyType": "APARTMENT",
                                                         "livingSpace": 100,
                                                         "numberOfRooms": 4,
                                                         "numberOfBedrooms": 2,
                                                         "price": 1200.0,
                                                         "propertyStatus": "INACTIVE",
                                                         "idHomeowner": 1
                                                         }
                                }
                            ]
                        }                              
                        """));

        verify(requestManager).getRequests(RequestStatus.PENDING);
    }


    private Request CreatePendingRequest(){

        return Request.builder()
                .id(2L)
                .property(CreateProperty())
                .homeowner(CreateHomeowner())
                .requestStatus(RequestStatus.PENDING)
                .date(LocalDateTime.of(2022, 11, 11, 20, 00))
                .build();
    }


    private Property CreateProperty(){
        return Property.builder()
                .id(3L)
                .propertyType(PropertyType.APARTMENT)
                .city("Amsterdam")
                .street("Pisano")
                .streetNumber(2)
                .postCode("1000")
                .price(1200)
                .numberOfRooms(4)
                .numberOfBedrooms(2)
                .propertyStatus(PropertyStatus.INACTIVE)
                .livingSpace(100)
                .idHomeowner(1L)
                .build();
    }

    private User CreateHomeowner(){
       return User.builder()
                .id(1L)
                .role(Role.HOMEOWNER)
                .firstName("Rositsa")
                .lastName("Nikolova")
                .build();
    }

}