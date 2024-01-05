package com.example.vaccination.service.impl;

import com.example.vaccination.exception.NotFoundException;
import com.example.vaccination.model.entity.Employee;
import com.example.vaccination.model.entity.VaccineType;
import com.example.vaccination.repository.EmployeeRepository;
import com.example.vaccination.service.EmployeeService;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    EmployeeRepository employeeRepository;

    @Override
    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee create(Employee employee) {
        String hashedPassword = BCrypt.hashpw(employee.getPassword(), BCrypt.gensalt());
        employee.setPassword(hashedPassword);
        employee.setStatus(true);
        return employeeRepository.saveAndFlush(employee);
    }

    @Override
    public Employee findByEmployeeID(String id) {
        Optional<Employee> result = Optional.ofNullable(employeeRepository.findByEmployeeID(id));
        if(result.isPresent()){
            return result.get();
        }
        throw  new NotFoundException("Could not find any employee with ID: " + id);
    }

    @Override
    public List<Employee> findAllByEmployeeName(String name) {
        return employeeRepository.findAllByEmployeeName(name);
    }

    @Override
    public List<Employee> findAllByPosition(String position) {
        return employeeRepository.findAllByPosition(position);
    }

    @Override
    public Employee findByUsername(String username) {
        return employeeRepository.findByUsername(username);
    }

    @Override
    public Employee delete(Employee employee) {
        employee.setStatus(false);
        return employeeRepository.saveAndFlush(employee);
    }

    @Override
    public Employee save(Employee employee) {
        return employeeRepository.saveAndFlush(employee);
    }

    @Override
    public List<Employee> activeEmployeeList() {
        return employeeRepository.findAllByStatusIsTrue();
    }

    @Override
    public boolean isPhoneExist(String phone) {
        Employee emp = null;
        emp = employeeRepository.findByPhone(phone);
        if(emp == null) return false;
        return true;
    }

    @Override
    public boolean isEmailExist(String email) {
        Employee emp = null;
        emp = employeeRepository.findByEmail(email);
        if(emp == null) return false;
        else return true;
    }

    @Override
    public boolean isUsernameExist(String username) {
        Employee emp = null;
        emp = employeeRepository.findByUsername(username);
        if(emp == null) return false;
        return true;
    }

    @Override
    public boolean isIDExist(String id) {
        Employee emp = null;
        emp = employeeRepository.findByEmployeeID(id);
        if(emp == null) return false;
        return true;
    }

    @Override
    public Employee findByEmail(String mail){
        return employeeRepository.findByEmail(mail);
    }


    public Employee saving(String mail, String pass){
        Employee exist = findByEmail(mail);
        String hashedPassword = BCrypt.hashpw(pass, BCrypt.gensalt());
        exist.setPassword(hashedPassword);
        exist.setStatus(true);
        return employeeRepository.save(exist);
    }
}
