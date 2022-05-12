package com.stockmanagement.config;

import com.stockmanagement.entity.Employee;
import com.stockmanagement.repository.EmployeeRepository;
import com.stockmanagement.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;


//@Service
//public class MyUserDetailsService implements UserDetailsService {
//
//    @Autowired
//    ProductRepository productRepository;
//    EmployeeRepository employeeRepository;
//
//    @Override
////    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//////        Optional<Employee> employee = employeeRepository.findByUserName(username);
//////        return new MyUserDetails(employee);
////    }
//}
