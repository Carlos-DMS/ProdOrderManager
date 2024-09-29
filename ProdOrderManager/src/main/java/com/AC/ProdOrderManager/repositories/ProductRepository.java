package com.AC.ProdOrderManager.repositories;

import com.AC.ProdOrderManager.models.product.ProductModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<ProductModel, String> {
    @Query("SELECT MAX(p.id) FROM ProductModel p WHERE p.id LIKE CONCAT(:initialIdNumbers, '%')")
    Optional<String> findLastIdForInitialIdNumbers(@Param("initialIdNumbers") int initialIdNumbers);

    @Query("SELECT p FROM ProductModel p WHERE p.id = :identifier OR p.name = :identifier")
    Optional<ProductModel> findByIdentifier(@Param("identifier") String identifier);
}