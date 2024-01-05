package com.example.vaccination.controller;

import com.example.vaccination.exception.NotFoundException;
import com.example.vaccination.handler.ResultMap;
import com.example.vaccination.model.dto.InjectionResultDto;
import com.example.vaccination.model.entity.*;
import com.example.vaccination.repository.CustomerRepository;
import com.example.vaccination.repository.InjectionResultRepository;
import com.example.vaccination.repository.VaccineRepository;
import com.example.vaccination.repository.VaccineTypeRepository;
import com.example.vaccination.service.InjectionResultService;
import com.example.vaccination.validator.InjectionResultDtoValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import java.text.ParseException;

import java.util.List;
import java.util.Optional;

@Controller
public class InjectionResultController {
    @Autowired
    private InjectionResultService injectionResultService;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private VaccineTypeRepository vaccineTypeRepository;
    @Autowired
    private VaccineRepository vaccineRepository;
    @Autowired
    private InjectionResultRepository injectionResultRepository;
    @Autowired
    private InjectionResultDtoValidator injectionResultDtoValidator;

    @GetMapping("/injectionResult")
    private String injectionresultpage(Model model) {
        List<InjectionResult> injectionResultList = injectionResultService.findAll();
        model.addAttribute("injectionResultList", injectionResultList);
        return "injectionResultList";
    }
    @GetMapping("/injectionResultCreateList")
    private String injectionResultCreateListPage(Model model) {
        List<InjectionResult> injectionResultList = injectionResultService.findAllByOrderByInjectionResultID();
        model.addAttribute("injectionResultList", injectionResultList);
        return "injectionResultList";
    }

    @GetMapping("/createInjectionResult")
    private String createinjectionresultpage(Model model, RedirectAttributes red) {
        InjectionResult creinjection = new InjectionResult();
        List<Customer> customerList = customerRepository.findAll();
        List<VaccineType> vaccineTypeList = vaccineTypeRepository.findAll();
        List<Vaccine> vaccineList = vaccineRepository.findAll();
        model.addAttribute("creinjection", creinjection);
        model.addAttribute("customerList", customerList);
        model.addAttribute("vaccineTypeList", vaccineTypeList);
        model.addAttribute("vaccineList", vaccineList);
        model.addAttribute("title", "Create New Injection Result");
        red.addFlashAttribute("title", "Create New Injection Result");
        return "createInjectionResult";
    }

    @PostMapping(value = "/createInjectionResult")
    private String createInjectionResult( @ModelAttribute("creinjection") InjectionResultDto injectionResultDto, Model model, RedirectAttributes red, BindingResult bindingResult) throws ParseException {
        injectionResultDtoValidator.validate(injectionResultDto, bindingResult);

        if (bindingResult.hasErrors()) {
            List<Vaccine> vaccineList = vaccineRepository.findAll();
            List<Customer> customerList = customerRepository.findAll();
            List<VaccineType> vaccineTypeList = vaccineTypeRepository.findAll();
            model.addAttribute("customerList", customerList);
            model.addAttribute("vaccineTypeList", vaccineTypeList);
            model.addAttribute("vaccineList", vaccineList);

            if (injectionResultDto.getInjectionDate().trim().isEmpty() || injectionResultDto.getNextInjectionDate().trim().isEmpty()) {
                InjectionResult creinjection = ResultMap.INSTANCE.stringToDate(injectionResultDto);
                if (creinjection.getInjectionResultID() != 0) {
                    model.addAttribute("title", "Update Injection Result");
                    return "/createInjectionResult";
                } else {
                    model.addAttribute("title", "Create New Injection Result");

                    return "/createInjectionResult";
                }
            }
            InjectionResult creinjection = ResultMap.INSTANCE.stringToDate(injectionResultDto);
            if (creinjection.getNextInjectionDate() != null) {
                if (creinjection.getNextInjectionDate().before(creinjection.getInjectionDate())) {
                    if (creinjection.getInjectionResultID() != 0) {
                        model.addAttribute("title", "Update Injection Result");
                        model.addAttribute("error", "Injection Date must be less than to Next Injection Date");
                        return "/createInjectionResult";
                    } else {
                        model.addAttribute("title", "Create New Injection Result");
                        model.addAttribute("error", "Injection Date must be less than to Next Injection Date");
                        return "/createInjectionResult";
                    }
                }
            }

            if (creinjection.getInjectionResultID() != 0) {
                model.addAttribute("title", "Update Injection Result");
                return "/createInjectionResult";
            } else {
                model.addAttribute("title", "Create New Injection Result");
                return "/createInjectionResult";
            }

        } else {

            List<Vaccine> vaccineList = vaccineRepository.findAll();
            List<Customer> customerList = customerRepository.findAll();
            List<VaccineType> vaccineTypeList = vaccineTypeRepository.findAll();
            model.addAttribute("customerList", customerList);
            model.addAttribute("vaccineTypeList", vaccineTypeList);
            model.addAttribute("vaccineList", vaccineList);
            InjectionResult creinjection = ResultMap.INSTANCE.stringToDate(injectionResultDto);

            if (creinjection.getNextInjectionDate().before(creinjection.getInjectionDate())) {
                if (creinjection.getInjectionResultID() != 0) {
                    model.addAttribute("title", "Update Injection Result");
                    red.addFlashAttribute("title", "Update Injection Result");
                    model.addAttribute("error", "Injection Date must be less than to Next Injection Date");
                    return "/createInjectionResult";
                } else {
                    Vaccine vaccine = vaccineRepository.findById(creinjection.getVaccine_r().getVaccineID()).orElse(null);
                    if (vaccine != null) {
                        int currentVaccineNumber = Integer.parseInt(vaccine.getNumberOfInjection());
                        int numberInjection = injectionResultDto.getNumberOfInjection();

                        if (currentVaccineNumber >= numberInjection) {
                            vaccine.setNumberOfInjection(String.valueOf(currentVaccineNumber - numberInjection));
                            vaccineRepository.save(vaccine);
                        } else {
                            model.addAttribute("error", "Number of Injection not enough!!!");
                            return "/createInjectionResult";
                        }
                    }
                    model.addAttribute("title", "Create New Injection Result");
                    red.addFlashAttribute("title", "Create New  Injection Result");
                    model.addAttribute("error", "Injection Date must be less than to Next Injection Date");
                    return "/createInjectionResult";
                }
            }
            //create
            if (creinjection.getInjectionResultID() == 0) {
                Vaccine vaccine = vaccineRepository.findById(creinjection.getVaccine_r().getVaccineID()).orElse(null);
                if (vaccine != null) {
                    int currentVaccineNumber = Integer.parseInt(vaccine.getNumberOfInjection());
                    int numberInjection = injectionResultDto.getNumberOfInjection();
                    if (currentVaccineNumber >= numberInjection) {
                        vaccine.setNumberOfInjection(String.valueOf(currentVaccineNumber - numberInjection));
                        vaccineRepository.save(vaccine);
                        creinjection.setVaccine_r(vaccine);
                        Customer customer = customerRepository.findById(creinjection.getCustomer().getCustomerID()).orElse(null);
                        creinjection.setCustomer(customer);
                        creinjection.setStatus(true);
                        int numberOfInjection = injectionResultDto.getNumberOfInjection();
                        creinjection.setNumberOfInjection(numberOfInjection);
                        injectionResultService.addInjectionResult(creinjection);
                        red.addFlashAttribute("successMessage", "Saved Sucessfull !!!");
                        return "redirect:/injectionResultCreateList";
                    } else {
                        model.addAttribute("error", "Number of Injection not enough!!!");
                        return "/createInjectionResult";
                    }
                }
            } else {
                //update
                InjectionResult injectionResult = injectionResultRepository.findById(creinjection.getInjectionResultID()).orElse(null);
                Vaccine vaccine = vaccineRepository.findById(creinjection.getVaccine_r().getVaccineID()).orElse(null);
                if (vaccine != null) {
                    int currentVaccineNumber = Integer.parseInt(vaccine.getNumberOfInjection()); //a
                    int numberInjection = injectionResult.getNumberOfInjection(); //b
                    int numberInjectionEnter = injectionResultDto.getNumberOfInjection(); //c
                    if (numberInjection <= numberInjectionEnter) {
                        vaccine.setNumberOfInjection(String.valueOf(currentVaccineNumber - (numberInjectionEnter - numberInjection)));
                        vaccineRepository.save(vaccine);
                        creinjection.setVaccine_r(vaccine);
                        int a = Integer.parseInt(vaccine.getNumberOfInjection());
                        Customer customer = customerRepository.findById(creinjection.getCustomer().getCustomerID()).orElse(null);
                        creinjection.setCustomer(customer);
                        creinjection.setStatus(true);
                        int numberOfInjection = injectionResultDto.getNumberOfInjection();
                        creinjection.setNumberOfInjection(numberOfInjection);
                        injectionResultService.addInjectionResult(creinjection);
                        red.addFlashAttribute("successMessage", "Saved Sucessfull !!!");
                        return "redirect:/injectionResultCreateList";
                    } else {
                        if (currentVaccineNumber >= numberInjectionEnter) {
                            vaccine.setNumberOfInjection(String.valueOf(currentVaccineNumber + (numberInjection - numberInjectionEnter)));
                            vaccineRepository.save(vaccine);
                            creinjection.setVaccine_r(vaccine);
                            int a = Integer.parseInt(vaccine.getNumberOfInjection());
                            Customer customer = customerRepository.findById(creinjection.getCustomer().getCustomerID()).orElse(null);
                            creinjection.setCustomer(customer);
                            creinjection.setStatus(true);
                            int numberOfInjection = injectionResultDto.getNumberOfInjection();
                            creinjection.setNumberOfInjection(numberOfInjection);
                            injectionResultService.addInjectionResult(creinjection);
                            red.addFlashAttribute("successMessage", "Saved Sucessfull !!!");
                            return "redirect:/injectionResultCreateList";
                        } else {
                            model.addAttribute("error", "Number of Injection not enough!!!");
                            return "/createInjectionResult";
                        }
                    }
                }
            }
            return "/createInjectionResult";
        }
    }

    //    @GetMapping(value = "injectionresultDelete")
//    public String deleteInjectionResult(@RequestParam int injectionResultID){
//        injectionResultService.deleteInjectionResultById(injectionResultID);
//        return "redirect:/injectionresult";
//    }
    @GetMapping(value = "injectionResultDelete")
    public String deleteInjectionResult(@RequestParam int injectionResultID){
        InjectionResult injectionResult = injectionResultService.getInjectionResultbyID(injectionResultID);

        if (injectionResult != null) {
            // Cập nhật trạng thái thành false thay vì xóa hoàn toàn
            injectionResult.setStatus(false);
            injectionResultService.updateInjectionResult(injectionResultID, injectionResult);
        }

        return "redirect:/injectionResult";
    }
    @PostMapping("/injectionResultDeleteWithCheckbox")
    public String deleteinjectionResult(@RequestParam(value = "injectionResultIds", required = false) List<String> injectionResultIds,RedirectAttributes red) {
        if (injectionResultIds != null) {
            for (String id : injectionResultIds) {
                try {
                    int injectionResultId = Integer.parseInt(id);
                    Optional<InjectionResult> injectionResult = injectionResultRepository.findById(injectionResultId);

                    if (injectionResult.isPresent()) {
                        injectionResult.get().setStatus(false);
                        injectionResultRepository.save(injectionResult.get());
                        red.addFlashAttribute("successMessage", "Save Succcessfull !!!");
                    }
                } catch (NumberFormatException e) {
                }
            }
            return "redirect:/injectionResult";
        }
        return "redirect:/injectionResult";
    }
    @GetMapping(value = "injectionResultEdit")
    public String editInjectionResult(@RequestParam int injectionResultID, Model model, RedirectAttributes red){
        try {
            InjectionResult creinjection = injectionResultService.getInjectionResultbyID(injectionResultID);
            List<Customer> customerList = customerRepository.findAll();
            List<VaccineType> vaccineTypeList = vaccineTypeRepository.findAll();
            List<Vaccine> vaccineList = vaccineRepository.findAll();
            model.addAttribute("creinjection", creinjection);
            model.addAttribute("customerList", customerList);
            model.addAttribute("vaccineTypeList", vaccineTypeList);
            model.addAttribute("vaccineList", vaccineList);
            model.addAttribute("title", "Update Injection Result");
            red.addFlashAttribute("title", "Update Injection Result ");
            return "createInjectionResult";
        }catch(NotFoundException e ){
            red.addFlashAttribute("messageError", e.getMessage());
            return "redirect:/injectionResult";
        }
    }
    @PostMapping(value = "updateInjectionResult")
    public String updateInjectionResult(@RequestParam int injectionResultID, @ModelAttribute("injectionResult") InjectionResult updatedResult, Model model, RedirectAttributes red ) {
        InjectionResult existingResult = injectionResultService.getInjectionResultbyID(injectionResultID);
        if (existingResult != null) {
            existingResult.setCustomer(updatedResult.getCustomer());
            existingResult.setPrevention(updatedResult.getPrevention());
            existingResult.setVaccine_r(updatedResult.getVaccine_r());
            existingResult.setNumberOfInjection(updatedResult.getNumberOfInjection());
            existingResult.setInjectionDate(updatedResult.getInjectionDate());
            existingResult.setNextInjectionDate(updatedResult.getNextInjectionDate());
            existingResult.setInjectionPlace(updatedResult.getInjectionPlace());

            // Lưu lại kết quả cập nhật
            injectionResultService.updateInjectionResult(injectionResultID, existingResult);
            red.addFlashAttribute("successMessage", "Save Succcessfull !!!");

        }
        return "redirect:/injectionResult";
    }
}
