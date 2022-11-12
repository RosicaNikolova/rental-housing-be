package org.example.controller.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.domain.RequestStatus;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RequestDTO {

    private Long id;
    private RequestStatus status;
    private LocalDateTime date;
    private String homeownerFirstName;
    private String homeownerLastName;
    private PropertyDTO property;

}
