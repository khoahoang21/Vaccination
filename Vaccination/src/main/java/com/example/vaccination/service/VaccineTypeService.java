package com.example.vaccination.service;

import com.example.vaccination.model.entity.VaccineType;

import java.util.List;
import java.util.Optional;

public interface VaccineTypeService {
    List<VaccineType> findAll();
    VaccineType save(VaccineType vaccineType);

    VaccineType findById(String id);

    VaccineType findByVaccineTypeID(String id);
    VaccineType findByVaccineTypeName(String name);
    List<VaccineType> findAllByStatus();
}
