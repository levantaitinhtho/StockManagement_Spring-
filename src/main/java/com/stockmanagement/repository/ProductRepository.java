package com.stockmanagement.repository;

import com.stockmanagement.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    @Modifying
    @Query(value = "UPDATE product set number = :number where id = :id", nativeQuery = true)
    void updateNumberProduct(@Param("id")Integer id, @Param("number") Integer number);

    @Query(value = "SELECT  * from product  WHERE name_product LIKE CONCAT('%',:name,'%')",  nativeQuery = true)
    List<Product> findByNameContaining(@Param("name")String term);
}
