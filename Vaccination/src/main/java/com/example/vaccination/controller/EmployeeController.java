package com.example.vaccination.controller;

import com.example.vaccination.exception.NotFoundException;
import com.example.vaccination.security.UserSecurity;
import com.example.vaccination.validator.EmployeeValidator;
import com.example.vaccination.model.entity.Employee;
import com.example.vaccination.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;

@Controller
public class EmployeeController {
    @Autowired
    EmployeeService employeeService;

    @Autowired
    EmployeeValidator employeeValidator;

    @GetMapping(value = "/employee")
    private String employeePage(Model model, @RequestParam(required = false) String msgW, @RequestParam(required = false) String msgS){
        List<Employee> employeeList = employeeService.findAllByPosition("employee");
        model.addAttribute("employeeList", employeeList);
        model.addAttribute("msgS", msgS);
        model.addAttribute("msgW", msgW);
        return "employeeList";
    }

    @GetMapping(value = "/createemp")
    private String createPage(Model model){
        Employee emp = new Employee();
        model.addAttribute("emp", emp);
        return "createEmployee";
    }

    @GetMapping(value = "/updateemp")
    private String updatePage(Model model, RedirectAttributes red, @RequestParam(value = "checkedid", required = false) String[] ids) {
          try {
              if (ids == null) return "redirect:/employee?msgW=Must choose 1 employee to update!!!";
              else if (ids.length != 1) return "redirect:/employee?msgW=Just choose 1 employee to update!!!";
              else {
                  Employee emp = employeeService.findByEmployeeID(Arrays.stream(ids).toList().get(0));
                  model.addAttribute("dob", emp.getDateOfBirth());
                  model.addAttribute("emp", emp);
                  return "updateEmployee";
              }
          }catch(NotFoundException e){
              red.addFlashAttribute("messageError", e.getMessage());
              return "redirect:/employee";
          }
    }


    @PostMapping(value = "/createemp")
    private String createNewEmployee(@ModelAttribute("emp") Employee employee, BindingResult bindingResult, @RequestParam(value = "imgFile", required = false) MultipartFile image, Model model) throws IOException {
        employeeValidator.validate(employee,bindingResult);
        if (bindingResult.hasErrors()) {
            return "createEmployee";
        }
        byte[] fileContent = image.getBytes();
        String encodedString = Base64.getEncoder().encodeToString(fileContent);
        employee.setImage(encodedString);
        employeeService.create(employee);
        return "redirect:/employee?msgS=Create Success!!";
    }

    @PostMapping(value = "/updateemp")
    private String updateEmployee(@ModelAttribute("emp") @Valid Employee employee, BindingResult bindingResult, @RequestParam(value = "imgFile", required = false) MultipartFile image, @RequestParam("oldImg") String oldImg, Model model) throws IOException {
        employeeValidator.validateForUpdate(employee,bindingResult);
        if (bindingResult.hasErrors()) {
            return "updateEmployee";
        }
        if(image.getSize() == 0) employee.setImage(oldImg);
        else{
            byte[] fileContent = image.getBytes();
            String encodedString = Base64.getEncoder().encodeToString(fileContent);
            employee.setImage(encodedString);
        }
        Employee result = employeeService.save(employee);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (employee.getEmployeeID().equals(((UserSecurity) authentication.getPrincipal()).getEmployee().getEmployeeID())) {
            authentication = new UsernamePasswordAuthenticationToken(new UserSecurity(result), authentication.getCredentials(), authentication.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        if(((UserSecurity) authentication.getPrincipal()).getEmployee().getPosition().equals("employee"))
            return "redirect:/home";
        return "redirect:/employee?msgS=Update Success!!!";
    }

    @GetMapping(value = "/deleteemployee")
    private String deleteEmployee(Model model, @RequestParam String[] ids){
        if(ids.length == 0)  return "redirect:/employee?msgW=Choose at least 1 to delete!!";
        boolean isWrong = false;
        String wrongId = "";
        for (String id : ids
        ) {
            if(!employeeService.findByEmployeeID(id).isStatus()){
                wrongId = id+" is inactive!!";
                isWrong = true;
                break;
            }
        }
        if(!isWrong) {
            for (String id : ids)   employeeService.delete(employeeService.findByEmployeeID(id));
            return "redirect:/employee?msgS=Deleted Success!!";
        }
        return "redirect:/employee?msgW=Id: "+wrongId;
    }

    @GetMapping(value = "/profile")
    private String updateProfile(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Employee emp = employeeService.findByEmployeeID(((UserSecurity) authentication.getPrincipal()).getEmployee().getEmployeeID());
        model.addAttribute("dob",emp.getDateOfBirth());
        model.addAttribute("emp",emp);
        return "updateEmployee";
    }
}
