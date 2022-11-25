package org.example.persistence;

import org.example.persistence.entity.RequestEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface JPARequestsRepository extends JpaRepository<RequestEntity, Long> {

    @Query(value = "SELECT r,p from RequestEntity r join r.propertyEntity p where p.id = r.propertyEntity.id")
    List<RequestEntity> getRequests();

    List<RequestEntity> findRequestEntitiesByUserEntity_Id(Long id);
}
