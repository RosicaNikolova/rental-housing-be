package org.example.persistence.entity;

import lombok.*;
import org.example.domain.PropertyStatus;
import org.example.domain.PropertyType;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "properties")
public class PropertyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "price")
    private double price;

    @Column(name="propertytype", columnDefinition = "ENUM('APARTMENT', 'HOUSE', 'ROOM', 'STUDIO')")
    @Enumerated(EnumType.STRING)
    private PropertyType propertyType;

    @Column(name = "city")
    private String city;

    @Column(name = "street")
    private String street;

    @Column(name = "streetnumber")
    private int streetNumber;

    @Column(name = "postcode")
    private String postcode;

    @Column(name = "livingspace")
    private int livingSpace;

    @Column(name = "numberofrooms")
    private int numberOfRooms;

    @Column(name = "numberofbedrooms")
    private int numberOfBedrooms;

    @Column(name = "propertystatus", columnDefinition = "ENUM('ACTIVE', 'INACTIVE')")
    @Enumerated(EnumType.STRING)
    private PropertyStatus propertyStatus;

    @OneToMany(mappedBy = "propertyEntity")
    private List<RequestEntity> requestEntityList;



}
