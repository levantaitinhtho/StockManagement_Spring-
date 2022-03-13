package com.stockmanagement.service.impl;

import com.stockmanagement.entity.Employee;
import com.stockmanagement.exception.ApplicationException;
import com.stockmanagement.model.MyUserPrincipal;
import com.stockmanagement.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;

import javax.annotation.PostConstruct;

@Service
@RequiredArgsConstructor
public class CustomUserDetailService {
//        implements UserDetailsService {
//
//    private WebApplicationContext applicationContext;
//
//    private EmployeeRepository userRepository;
//
//    @PostConstruct
//    public void completeSetup() {
//        userRepository = applicationContext.getBean(EmployeeRepository.class);
//    }
//
//    @Override
//    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
//        Employee user = userRepository.findByUserName(userName);
//
//        if (user == null) {
//            throw new ApplicationException("Username " + userName + " not found or password is not correct" );
//        }
//        if (user.getStatus().equals("deactive")) {
//            throw new ApplicationException("Username " + userName + " deactive");
//        }
//
//        System.out.println("Found User: " + user.getUserName() + "         role: " + user.getRole());
//        return new MyUserPrincipal(user);
//    }

}
