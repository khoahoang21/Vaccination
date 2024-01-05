package com.example.vaccination.controller;

import com.example.vaccination.service.InjectionResultService;
import com.example.vaccination.service.impl.CustomerServiceImpl;
import com.example.vaccination.service.impl.VaccineServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class ChartController {

    @Autowired
    private VaccineServiceImpl vaccineService;

    @Autowired
    private CustomerServiceImpl customerService;

    @Autowired
    private InjectionResultService injectionResultService;

    // Injection results chart
    @GetMapping("/reportinjectionresultchart")
    public String getChartData(Model model,
                               @RequestParam(name = "yearSelection", required = false) String yearSelect) {

        List<String> injectionResultList = injectionResultService.count(yearSelect);
        model.addAttribute("yearSelection", yearSelect);
        model.addAttribute("injectionResultList",injectionResultList);

        return "reportInjectionResultChart";
    }

    // Vaccine chart
    @GetMapping("/reportvaccinechart")
    public String getVaccineChartData(Model model,
                                @RequestParam(name = "yearSelection", required = false) String yearSelect) {
        List<String> vaccineList = vaccineService.count(yearSelect);
        model.addAttribute("yearSelection", yearSelect);
        model.addAttribute("vaccineList",vaccineList);

        return "reportVaccine-chart";
    }


    // Customers chart
    @GetMapping("/reportcustomerchart")
    public String getCustomerChartData(Model model,
                                      @RequestParam(name = "yearSelection", required = false) String yearSelect) {
        List<String>  customerList = customerService.chart(yearSelect);
        model.addAttribute("yearSelection", yearSelect);
        model.addAttribute("customerList",customerList);
        System.out.println(customerList);

        return "reportCustomer-chart";
    }



}