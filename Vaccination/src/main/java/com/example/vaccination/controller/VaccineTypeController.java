package com.example.vaccination.controller;

import com.example.vaccination.exception.NotFoundException;
import com.example.vaccination.model.entity.VaccineType;
import com.example.vaccination.repository.VaccineRepository;
import com.example.vaccination.repository.VaccineTypeRepository;
import com.example.vaccination.service.VaccineTypeService;
import com.example.vaccination.validator.VaccineTypeValidator;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Base64;
import java.util.List;
import java.util.Optional;


@Controller
public class VaccineTypeController {

    @Autowired
    private VaccineTypeService vaccineTypeService;

    @Autowired
    private VaccineTypeRepository vaccineTypeRepository;

    @Autowired
    private VaccineTypeValidator vaccineTypeValidator;

    @Autowired
    private VaccineRepository vaccineRepository;


    @GetMapping(value = "/vaccineTypeList")
    public String listVaccineType(Model model){
        List<VaccineType> vaccineType = vaccineTypeService.findAll();
        model.addAttribute("vaccineType", vaccineType);
        return "vaccineTypeList";
    }


    @GetMapping("/createVaccineType")
    public String showVaccineTypeForm(Model model) {
        model.addAttribute("vaccineType", new VaccineType());
        return "createVaccineType";
    }

    @PostMapping(value = "/createVaccineType")
    public String summit(Model model,RedirectAttributes red, @ModelAttribute("vaccineType") @Valid VaccineType vaccineType,
                         BindingResult bindingResult, @RequestParam MultipartFile img) throws IOException {

        vaccineTypeValidator.validate(vaccineType,bindingResult);

        if (bindingResult.hasErrors()) {
            model.addAttribute("vaccineType",vaccineType);
                if(img.getSize() > 5242880){
                    bindingResult.rejectValue("image", "error.image", "Image size should not exceed 5 MB");
                    return "createVaccineType";
                }
            return "createVaccineType";
        }
        byte[] fileContent = img.getBytes();
        String encodedString = Base64.getEncoder().encodeToString(fileContent);
        vaccineType.setImage(encodedString);
        red.addFlashAttribute("message", "Save Succcessfull !!!");
        vaccineTypeService.save(vaccineType);
        return "redirect:/vaccineTypeList";
    }

    @GetMapping("/updateVaccineType")
    public String showVaccineTypeUpdateForm(@RequestParam("id") String id, Model model, RedirectAttributes red) {

        try {
            VaccineType existingVaccineType = vaccineTypeService.findById(id);
            model.addAttribute("vaccineType", existingVaccineType);
            return "updateVaccineType";
        }catch(NotFoundException e){
            red.addFlashAttribute("messageError", e.getMessage());
            return "redirect:/vaccineTypeList";
        }
    }

    @PostMapping(value = "/updateVaccineType")
    public String change(Model model, RedirectAttributes red, @ModelAttribute("vaccineType") @Valid VaccineType vaccineType,
                         BindingResult bindingResult, @RequestParam MultipartFile img,
                         @RequestParam("old") String old ) throws IOException {

        vaccineTypeValidator.validateUpdate(vaccineType,bindingResult);
        if (img.getSize() ==0){
            vaccineType.setImage(old);}

        if (bindingResult.hasErrors()) {
            model.addAttribute("vaccineType",vaccineType);
            if(img.getSize() > 5242880){
                bindingResult.rejectValue("image", "error.image", "Image size should not exceed 5 MB");
                return "updateVaccineType";
            }
            return "updateVaccineType";
        }

        vaccineTypeService.save(vaccineType);

        if (img.getSize() > 0) {
            byte[] fileContent = img.getBytes();
            String encodedString = Base64.getEncoder().encodeToString(fileContent);
            vaccineType.setImage(encodedString);
            vaccineTypeService.save(vaccineType);
        }
        red.addFlashAttribute("message", "Save Succcessfull !!!");

        return "redirect:/vaccineTypeList";
    }


    @PostMapping("/delete")
    public String deleteVaccineTypes(@RequestParam(value = "vaccineIds", required = false) List<String> vaccineIds, RedirectAttributes red) {
        if (vaccineIds != null) {
            for (String id : vaccineIds) {
                Optional<VaccineType> vaccineType = vaccineTypeRepository.findById(id);
                if (vaccineType.isPresent()) {
                    vaccineType.get().setStatus(false);
                    vaccineType.get().getVaccineList().forEach(vaccine -> vaccine.setStatus(false));
                    vaccineRepository.saveAll(vaccineType.get().getVaccineList());
                    vaccineTypeRepository.save(vaccineType.get());
                    red.addFlashAttribute("message", "Make-InActive Succcessfull !!!");
                }
            }
        }
        return "redirect:/vaccineTypeList";
    }

}



