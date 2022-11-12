package org.example.persistence.entity;

import lombok.*;
import org.example.domain.Property;
import org.example.domain.RequestStatus;
import org.example.domain.User;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "requests")
public class RequestEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idrequest")
    private Long id;

    @Column(name = "date")
    private LocalDateTime date;

//    @Column(name = "idhomeowner")
//    private Long homeownerId;
//
//    @Column(name = "idproperty")
//    private Long propertyId;

    @Column(name="status", columnDefinition = "ENUM('PENDING', 'REJECTED', 'ACCEPTED')")
    @Enumerated(EnumType.STRING)
    private RequestStatus requestStatus;

    @Column(name = "resonrejection")
    private String reasonRejection;

    @ManyToOne()
    @JoinColumn(name="idproperty")
    private PropertyEntity propertyEntity;

    @ManyToOne()
    @JoinColumn(name="idhomeowner")
    private UserEntity userEntity;


}
