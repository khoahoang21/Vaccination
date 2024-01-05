package com.example.vaccination.service;

import com.example.vaccination.model.entity.Customer;
import java.util.List;


public interface CustomerService {
    void save(Customer customer);
    List<Customer> findAll();
    Customer findById(int id);
    void deleteById(int id);
}
