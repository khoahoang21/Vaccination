package com.example.vaccination.service.impl;

import com.example.vaccination.exception.NotFoundException;
import com.example.vaccination.model.entity.InjectionResult;
import com.example.vaccination.model.entity.Vaccine;
import com.example.vaccination.repository.VaccineRepository;
import com.example.vaccination.service.VaccineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class VaccineServiceImpl implements VaccineService {

    @Autowired
    private VaccineRepository repository;

    @Override
    public Vaccine save(Vaccine vaccine){
        return repository.save(vaccine);
    }

    public List<Vaccine> getAllVaccine() {
        return this.repository.findAll();
    }

    public Vaccine addNew(Vaccine p) {
        return repository.save(p);
    }

    public void saveByExcel(List<Vaccine> file) {
        this.repository.saveAll(file);
    }

    public Vaccine findByVaccineName(String name) {
        try {
            return repository.findByVaccineName(name).orElse(null);
        } catch (NotFoundException ex) {
            throw ex;
        }
    }

    public Vaccine findById(String id) {
        Optional<Vaccine> result = repository.findById(id);
        if(result.isPresent()){
            return result.get();
        }
        throw  new NotFoundException("Could not find any vaccine with ID: " + id);
    }


    // Search for Vaccine Report
    public List<Vaccine> findByOptions(Date from, Date to, String vaccineSelection, String origin) {

        if (from != null || to != null || vaccineSelection != null || origin != null)
            return repository.findAllbyConditions(from, to, vaccineSelection, origin);

        return getAllVaccine();
    }

    @Autowired
    public VaccineServiceImpl(VaccineRepository vaccineRepository) {
        this.repository = vaccineRepository;
    }

    //Query by year
    public List<String> count(String yearSelect){
        return repository.CountVaccine(yearSelect);
    }


}
