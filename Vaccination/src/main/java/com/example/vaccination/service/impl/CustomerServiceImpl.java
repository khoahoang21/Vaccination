package com.example.vaccination.service.impl;

import com.example.vaccination.model.entity.Customer;
import com.example.vaccination.repository.CustomerRepository;
import com.example.vaccination.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public void save(Customer customer) {
        customerRepository.save(customer);
    }

    @Override
    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    @Override
    public Customer findById(int id) {
        Optional<Customer> result = customerRepository.findById(id);
        Customer customer = null;
        if (result.isPresent()) {
            customer = result.get();
        }
        else {
            throw new RuntimeException("Could not find any customer with ID: " + id);
        }
        return customer;
    }

    @Override
    public void deleteById(int id) {
        customerRepository.deleteById(id);
    }

    // Injection count for customers report
    public List<Customer> getCustomersWithInjectionCount(Date from, Date to, String fullName, String address) {
        List<Customer> customers = customerRepository.findAll();

        for (Customer customer : customers) {
            Long numberOfInject = customerRepository.getInjectionCountByCustomerId((long) customer.getCustomerID());
            customer.setNumberOfInject(numberOfInject);
        }

        // Filter by from, to, full name, address
        if(from != null || to != null || fullName != null || address != null)
            return customerRepository.customeFilter(from, to, fullName, address);
        return customers;
    }

    //Customer chart
    public List<String> chart(String yearSelect){
        return customerRepository.countCustomer(yearSelect);
    }

}