package com.sparta.spartaeats.store.repository;

import com.sparta.spartaeats.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.UUID;

public interface StoreRepository extends JpaRepository<com.sparta.spartaeats.entity.Store, UUID> {

    @Query("select s from Store s where s.id = :storeId and (s.delYn = 'N' or s.delYn = 'n')")
    Optional<Store> findByIdWithDel(@Param("storeId") UUID storeId);


}