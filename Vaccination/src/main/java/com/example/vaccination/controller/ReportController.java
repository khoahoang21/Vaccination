package com.example.vaccination.controller;

import com.example.vaccination.model.entity.*;
import com.example.vaccination.service.impl.CustomerServiceImpl;
import com.example.vaccination.service.impl.InjectionResultServiceImpl;
import com.example.vaccination.service.impl.VaccineServiceImpl;
import com.example.vaccination.service.impl.VaccineTypeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Date;
import java.util.List;

@Controller
public class ReportController {
    @Autowired
    private VaccineServiceImpl vaccineService;

    @Autowired
    private VaccineTypeServiceImpl vaccineTypeService;

    @Autowired
    private com.example.vaccination.service.NewsServices NewsServices;

    @Autowired
    private CustomerServiceImpl customerService;

    @Autowired
    private InjectionResultServiceImpl injectionResultService;

    // Schedule Report
    @GetMapping(value = "/reportinjectionresult-og")
    public String reportResult(Model model) {

        List<InjectionResult> injectionResultList = injectionResultService.findAll();
        List<VaccineType> vaccineTypeList = vaccineTypeService.findAll();
        model.addAttribute("vaccineTypeList",vaccineTypeList);
        model.addAttribute("injectionResultList", injectionResultList);

        return "reportInjectionResult-original";
    }

    @GetMapping(value = "/reportinjectionresult")
    public String reportResult2(Model model, RedirectAttributes red,
                                @RequestParam(value = "startDate", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
                                @RequestParam(value = "endDate", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate,
                                @RequestParam(value = "vaccineTypeName", required = false) String vaccineTypeName,
                                @RequestParam(value = "prevention", required = false) String prevention) {

        List<InjectionResult> injectionResultList = injectionResultService.searchResults(startDate, endDate, vaccineTypeName, prevention);

        List<VaccineType> vaccineTypeList = vaccineTypeService.findAll(); // find selection

        model.addAttribute("vaccineTypeList",vaccineTypeList);
        model.addAttribute("injectionResultList", injectionResultList);
        model.addAttribute("startDate",startDate);
        model.addAttribute("endDate",endDate);
        model.addAttribute("prevention",prevention);
        model.addAttribute("vaccineTypeName",vaccineTypeName);

        return "reportInjectionResult";
    }

    // Customer Report
    @GetMapping(value = "/reportcustomer")
    public String reportCustomer(Model model,
                                 @RequestParam(name = "from", required = false) @DateTimeFormat(pattern="yyyy-MM-dd") Date from,
                                 @RequestParam(name = "to", required = false) @DateTimeFormat(pattern="yyyy-MM-dd") Date to,
                                 @RequestParam(name="fullName", required = false) String fullName,
                                 @RequestParam(name="address", required = false) String address) {

        List<Customer> customers = customerService.getCustomersWithInjectionCount(from, to, fullName, address); // Use transient to get number of inject by customer_id
        model.addAttribute("customers", customers);
        model.addAttribute("from", from);
        model.addAttribute("to", to);
        model.addAttribute("fullName", fullName);
        model.addAttribute("address", address);

        return "reportCustomer";
    }

    // Vaccine Report
    @GetMapping(value = "/reportvaccine")
    public String reportVaccine(@RequestParam(name = "from", required = false) @DateTimeFormat(pattern="yyyy-MM-dd") Date from,
                                @RequestParam(name = "to", required = false) @DateTimeFormat(pattern="yyyy-MM-dd") Date to,
                                @RequestParam(name = "vaccineSelection", required = false) String vaccineSelection,
                                @RequestParam(name = "origin", required = false) String origin, Model model) {

        List<Vaccine> vaccineList = vaccineService.findByOptions(from, to, vaccineSelection,origin);
        List<VaccineType> vaccineTypeList = vaccineTypeService.findAll();
        model.addAttribute("vaccineList", vaccineList);
        model.addAttribute("vaccineTypeList", vaccineTypeList);
        model.addAttribute("from",from);
        model.addAttribute("to",to);
        model.addAttribute("vaccineSelection",vaccineSelection);
        model.addAttribute("origin",origin);
        return "reportVaccine";
    }


}




