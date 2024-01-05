package com.example.vaccination.service;

import com.example.vaccination.model.entity.Employee;

import java.util.List;

public interface EmployeeService {
    List<Employee> findAll();

    Employee create(Employee emp);

    Employee findByEmployeeID(String id);

    List<Employee> findAllByEmployeeName(String name);

    List<Employee> findAllByPosition(String position);

    Employee findByUsername(String username);

    Employee save(Employee employee);

    Employee delete(Employee employee);

    boolean isIDExist(String id);

    boolean isPhoneExist(String phone);

    boolean isEmailExist(String email);

    boolean isUsernameExist(String username);

    List<Employee> activeEmployeeList();

    Employee findByEmail(String mail) throws Exception;

}
