package com.stockmanagement.repository;

import com.stockmanagement.entity.ImportProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImportProductRepository extends JpaRepository<ImportProduct, Integer> {

    @Query(value = "SELECT  p.id AS product_id, p.name_product as name_product, e.id as user_id, e.user_name as user_name, imp.number as number, imp.date_update as date_update FROM import_product imp"
            + " INNER JOIN product p ON  imp.product_id = p.id "
            +	"INNER JOIN employee e ON  imp.user_id =e.id"
            + " WHERE imp.number = COALESCE(:number, imp.number)"
            + " and date_update LIKE '%' + COALESCE(:date, date_update) + '%'"
            + " and user_name LIKE '%' + COALESCE(:user_name, user_name) + '%'"
            + " and p.name_product LIKE '%' + COALESCE(:name_product, p.name_product) + '%'"
            + " and p.id = COALESCE(:product_id, p.id) and e.id = COALESCE(:employee_id, e.id)", nativeQuery = true)

    public List<Object[]> searchImportProduct(@Param("number") Integer number, @Param("date") String date, @Param("user_name") String userName,
                                              @Param("name_product") String nameProduct, @Param("product_id") Integer productId, @Param("employee_id") Integer employeeId);
}