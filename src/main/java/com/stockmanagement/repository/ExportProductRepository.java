package com.stockmanagement.repository;

import com.stockmanagement.entity.ExportProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExportProductRepository extends JpaRepository<ExportProduct, Integer> {

    @Query(value = "SELECT  p.id AS product_id, p.name_product as name_product, e.id as user_id, e.user_name as user_name, exp.number as number, exp.date_export as date_export FROM export_product exp"
            + " INNER JOIN product p ON  exp.product_id = p.id "
            +	"INNER JOIN employee e ON  exp.user_id =e.id"
            + " WHERE exp.number = COALESCE(:number, exp.number)"
            + " and date_export LIKE '%' + COALESCE(:date, date_export) + '%'"
            + " and user_name LIKE '%' + COALESCE(:user_name, user_name) + '%'"
            + " and p.name_product LIKE '%' + COALESCE(:name_product, p.name_product) + '%'"
            + " and p.id = COALESCE(:product_id, p.id) and e.id = COALESCE(:employee_id, e.id)", nativeQuery = true)

    public List<Object[]> searchExportProduct(@Param("number") Integer number, @Param("date") String date, @Param("user_name") String userName,
                                              @Param("name_product") String nameProduct, @Param("product_id") Integer productId, @Param("employee_id") Integer employeeId);

}
