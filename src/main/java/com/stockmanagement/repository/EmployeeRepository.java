package com.stockmanagement.repository;

import com.stockmanagement.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

        Employee findByUserName(String userName);

        @Query("SELECT e from Employee e WHERE e.userName LIKE CONCAT('%', :user_name, '%')")
        List<Employee> searchByName(@Param("user_name") String term);

        @Modifying
        @Query(value = "UPDATE Employee e SET e.status = 'deactive' WHERE e.id = :id")
        void deactiveEmployee(@Param("id") Integer id);
}

