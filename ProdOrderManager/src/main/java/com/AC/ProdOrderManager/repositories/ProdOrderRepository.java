package com.AC.ProdOrderManager.repositories;

import com.AC.ProdOrderManager.models.prodOrder.ProdOrderModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProdOrderRepository extends JpaRepository<ProdOrderModel, String> {
    @Query(value = "SELECT id FROM prod_order WHERE SUBSTRING(id, 6, 2) = :currentYear ORDER BY CAST(SUBSTRING(id, 1, 4) AS UNSIGNED) DESC LIMIT 1", nativeQuery = true)
    String findLastIdForYear(@Param("currentYear") String currentYear);
}

