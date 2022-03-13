package com.stockmanagement.service.base;

import com.stockmanagement.entity.Employee;

import java.util.List;

public interface EmployeeService {
   List<Employee> getAllEmployees();
    Employee getEmployeeById(Integer id);
    void addEmployee(Employee employee);
    void deactiveEmployee(Integer Id);
    Employee findByName(String name);
    List<Employee> searchEmployees(String term);
}
