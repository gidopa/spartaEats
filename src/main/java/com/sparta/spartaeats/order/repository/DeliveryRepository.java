package com.sparta.spartaeats.order.repository;

import com.sparta.spartaeats.entity.Delivery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.UUID;

public interface DeliveryRepository extends JpaRepository<Delivery, UUID> {

    @Query("select d from Delivery d where d.id = :storeId and d.delYn = 'N'")
    Optional<Delivery> findByIdWithDel(UUID deliveryId);
}
