package com.example.vaccination.controller;

import com.example.vaccination.exception.NotFoundException;
import com.example.vaccination.validator.VaccineValidator;

import com.example.vaccination.model.entity.Vaccine;
import com.example.vaccination.model.entity.VaccineType;
import com.example.vaccination.repository.VaccineRepository;
import com.example.vaccination.service.VaccineTypeService;
import com.example.vaccination.service.impl.Helper;
import com.example.vaccination.service.impl.VaccineServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
public class VaccineController {

    @Autowired
    private Helper helper;

    @Autowired
    private VaccineServiceImpl service;

    @Autowired
    private VaccineRepository repository;

    @Autowired
    private VaccineValidator vaccineValidator;

    @Autowired
    private VaccineTypeService vaccineTypeService;


    @GetMapping("/productall")
    public List<Vaccine> getAllProduct() {
        return service.getAllVaccine();
    }

    @GetMapping(path = "/vaccineList")
    public String findAll(Model model) {
        List<Vaccine> listVaccine = service.getAllVaccine();
        model.addAttribute("listVaccine", listVaccine);
        return "vaccineList";
    }

    @GetMapping(path = "/createVaccine")
    public String showRegisterForm(Model model) {
        List<VaccineType> vaccineTypesList = vaccineTypeService.findAllByStatus();
        model.addAttribute("vaccineTypesList", vaccineTypesList);
        model.addAttribute("vaccine", new Vaccine());
        return "createVaccine";
    }

    @PostMapping(path = "/createVaccine")
    public String save(Model model, @ModelAttribute("vaccine") @Valid Vaccine vaccine, BindingResult bindingResult, RedirectAttributes red) {
        vaccineValidator.validate(vaccine, bindingResult);
        List<VaccineType> vaccineTypesList = vaccineTypeService.findAllByStatus();
        model.addAttribute("vaccineTypesList", vaccineTypesList);
        if (bindingResult.hasErrors()) {
            model.addAttribute("vaccine", vaccine);
            return "createVaccine";
        }
        if (vaccine.getTimeBeginNextInjection().after(vaccine.getTimeEndNextInjection())) {
            model.addAttribute("error", "TimeBegin must be less than TimeEnd");
            return "updateVaccine";
        }
        service.addNew(vaccine);
        red.addFlashAttribute("message", "Save Succcessfull !!!");
        return "redirect:/vaccineList";
    }


    @GetMapping(path = "/vaccineEdit")
    public String editVaccineForm(@RequestParam("id") String id, Model model, RedirectAttributes red) {
        try {
            List<VaccineType> vaccineTypesList = vaccineTypeService.findAll();
            Vaccine exists = service.findById(id);
            model.addAttribute("vaccineTypesList", vaccineTypesList);
            model.addAttribute("vaccine", exists);
            return "updateVaccine";
        }catch(NotFoundException e){
                red.addFlashAttribute("messageError", e.getMessage());
                return "redirect:/vaccineList";
            }
    }

    @PostMapping(path = "/vaccineEdit")
    public String updateVaccine(@ModelAttribute("vaccine") @Valid Vaccine vaccine, BindingResult bindingResult, Model model, RedirectAttributes red) {
        List<VaccineType> vaccineTypesList = vaccineTypeService.findAll();
        model.addAttribute("vaccineTypesList", vaccineTypesList);
        vaccineValidator.validateforUpdate(vaccine, bindingResult);
        if (bindingResult.hasErrors()) {
            return "updateVaccine";
        }
        if (vaccine.getTimeBeginNextInjection().after(vaccine.getTimeEndNextInjection())) {
            model.addAttribute("error", "TimeBegin must be less than TimeEnd");
            return "updateVaccine";
        }
        service.addNew(vaccine);
        red.addFlashAttribute("message", "Save Succcessfull !!!");
        return "redirect:/vaccineList";
    }

    @PostMapping(value = "/vaccine/delete")
    public String deleteVaccineTypes(@RequestParam(value = "vaccineIds", required = false) List<String> vaccineIds, RedirectAttributes red) {
        if (vaccineIds != null) {
            for (String id : vaccineIds) {
                Optional<Vaccine> vaccineType = repository.findById(id);
                if (vaccineType.isPresent()) {
                    vaccineType.get().setStatus(false);
                    repository.save(vaccineType.get());
                }
            }
        }
        red.addFlashAttribute("message", "Make-InActive Succcessfull !!!");
        return "redirect:/vaccineList";
    }

    @GetMapping(path = "/vaccineUpload")
    public String upload(Model model) {
        model.addAttribute("vaccine", new Vaccine());
        return "uploadByExcel";
    }

    @PostMapping("/vaccineUpload")
    public String upload(@ModelAttribute("file") MultipartFile file, Model model) {
        if (helper.checkExcelFormat(file)) {
            try {
                List<Vaccine> vaccineImport = helper.convertExcelToListOfProduct(file.getInputStream());
                this.service.saveByExcel(vaccineImport);
                model.addAttribute("uploadSuccess", true);
            }catch (Exception e)
            {
                model.addAttribute("uploadErrors", e.getMessage());
            }
        }
        return "uploadByExcel";
    }



}