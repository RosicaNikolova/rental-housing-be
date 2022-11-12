package org.example.persistence;


import org.example.persistence.entity.PropertyEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JPAPropertyRepository extends JpaRepository<PropertyEntity, Long> {
    List<PropertyEntity> findFirstByPostcodeAndPrice(String postCode, double price);
}
