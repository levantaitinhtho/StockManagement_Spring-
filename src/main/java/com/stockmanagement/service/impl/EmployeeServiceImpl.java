package com.stockmanagement.service.impl;

import com.stockmanagement.entity.Employee;
import com.stockmanagement.repository.EmployeeRepository;
import com.stockmanagement.service.base.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

//    @Autowired
//    private PasswordEncoder passwordEncoder;

    @Override
    public List<Employee> getAllEmployees() {
        List<Employee> allEmployees = new ArrayList<>();
        List<Employee> employees = employeeRepository.findAll();
        if (!CollectionUtils.isEmpty(employees)) {
            employees.forEach(allEmployees::add);
        }
        return allEmployees;
    }

    @Override
    public Employee getEmployeeById(Integer id) {
        return employeeRepository.findById(id).get();
    }

    @Override
    public void addEmployee(Employee employee) {
        employee.setRole("ROLE_USER");
        employee.setStatus("active");
 //       employee.setPassword(passwordEncoder.encode(employee.getRawPassword()));
        employeeRepository.save(employee);

    }

    @Override
    public void deactiveEmployee(Integer id) {
        employeeRepository.deactiveEmployee(id);

    }

    @Override
    public Employee findByName(String name) {
        return employeeRepository.findByUserName(name);
    }

    @Override
    public List<Employee> searchEmployees(String term) {
        return employeeRepository.searchByName(term);
    }

}