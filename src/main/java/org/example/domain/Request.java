package org.example.domain;

import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Request {

    private Long id;
    private LocalDateTime date;
    private User homeowner;
    private Property property;
    private RequestStatus requestStatus;
    private String reasonRejection;

}
