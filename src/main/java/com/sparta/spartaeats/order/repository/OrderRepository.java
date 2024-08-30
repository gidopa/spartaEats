package com.sparta.spartaeats.order.repository;


import com.sparta.spartaeats.order.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.UUID;

public interface OrderRepository extends JpaRepository<Order, UUID>, OrderRepositoryCustom {

    @Query("select o from Order o where o.id = :orderId and o.user.id = :userId and (o.user.delYn='N' or o.user.delYn='n')")
    Optional<Object> findByIdWithAuthDel(@Param("orderId") UUID orderId, @Param("userId") Long userId);

}
