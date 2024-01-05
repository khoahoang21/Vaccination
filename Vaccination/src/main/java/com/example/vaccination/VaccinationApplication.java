package com.example.vaccination;

import com.example.vaccination.model.entity.Employee;
import com.example.vaccination.service.EmployeeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.text.SimpleDateFormat;
import java.util.Date;

@SpringBootApplication
public class VaccinationApplication  implements CommandLineRunner {

    @Autowired
    EmployeeService employeeService;

    public static void main(String[] args){
        SpringApplication.run(VaccinationApplication.class, args);
    }
    @Override
    public void run(String... args) throws Exception {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date = formatter.parse("2002-04-07");
        if(!employeeService.isIDExist("EM001")) {
            employeeService.create(new Employee("EM001", "here", date, "phamthaiduy963258741@gmail.com", "Cuong", true, "EM001.jpg", "1", "0352312321", "admin", "admin", "tp1", true));
            employeeService.create(new Employee("EM002", "here", date, "nongtraicau3@gmail.com", "Kuong", true, "EM002.jpg", "1", "0352315551", "admin", "a", "tp1", true));
            employeeService.create(new Employee("EM003", "here", date, "cuong3@gmail.com", "Kuong", true, "EM003.jpg", "1", "0352315552", "admin", "b", "tp1", true));
            employeeService.create(new Employee("EM004", "here", date, "dangkhoahoang39@gmail.com", "Kuong", true, "EM004.jpg", "1", "0352315553", "employee", "c", "tp1", true));
            employeeService.create(new Employee("EM005", "here", date, "cuong5@gmail.com", "Kuong", true, "EM005.jpg", "1", "0352315554", "employee", "d", "tp1", true));
            employeeService.create(new Employee("EM006", "here", date, "cuong6@gmail.com", "Kuong", true, "EM006.jpg", "1", "0352315555", "employee", "e", "tp1", true));
            employeeService.create(new Employee("EM007", "here", date, "cuong7@gmail.com", "Kuong", true, "EM007.jpg", "1", "0352315556", "employee", "f", "tp1", true));
        }
    }
}
