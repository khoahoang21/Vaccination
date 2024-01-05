package com.example.vaccination.service.impl;

import com.example.vaccination.exception.NotFoundException;
import com.example.vaccination.model.entity.VaccineType;
import com.example.vaccination.repository.VaccineTypeRepository;
import com.example.vaccination.service.VaccineTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VaccineTypeServiceImpl implements VaccineTypeService {

    @Autowired
    VaccineTypeRepository vaccineTypeRepository;
    @Override
    public List<VaccineType> findAll() {
        return vaccineTypeRepository.findAll();
    }

    @Override
    public VaccineType save(VaccineType vaccineType) {
        return vaccineTypeRepository.saveAndFlush(vaccineType);
    }

    @Override
    public VaccineType findById(String id) {

        Optional<VaccineType> result = vaccineTypeRepository.findById(id);
        if(result.isPresent()){
            return result.get();
        }
        throw  new NotFoundException("Could not find any vaccine type with ID: " + id);
    }

    @Override
    public VaccineType findByVaccineTypeID(String id) {
        return vaccineTypeRepository.findById(id).orElse(null);
    }

    @Override
    public List<VaccineType> findAllByStatus() {
        return vaccineTypeRepository.findAllByStatus(true);
    }


    @Override
    public VaccineType findByVaccineTypeName(String name) {
        return vaccineTypeRepository.findByVaccineTypeName(name).orElse(null);
    }

}
