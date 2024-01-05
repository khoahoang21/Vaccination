package com.example.vaccination.service.impl;

import com.example.vaccination.exception.NotFoundException;
import com.example.vaccination.model.entity.InjectionResult;
import com.example.vaccination.model.entity.InjectionSchedule;
import com.example.vaccination.repository.InjectionResultRepository;
import com.example.vaccination.service.InjectionResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class InjectionResultServiceImpl implements InjectionResultService {

    @Autowired
    private InjectionResultRepository injectionResultRepository;
    @Override
    public List<InjectionResult> findAll() {
        return injectionResultRepository.findAll();
    }

    @Override
    public List<InjectionResult> findAllByOrderByInjectionResultID() {
        return injectionResultRepository.findAllByOrderByInjectionResultIDDesc();
    }

    ////////////---search
    @Override
    public List<InjectionResult> searchResults(Date startDate, Date endDate, String vaccineTypeName, String prevention) {

        if (startDate != null || endDate != null || vaccineTypeName != null || prevention != null)
            return injectionResultRepository.findResults(startDate, endDate, vaccineTypeName, prevention);

        return findAll();
    }

    @Override
    public List<String> count(String yearSelect) {
        return injectionResultRepository.CountInjectionResult(yearSelect);
    }

    @Override
    public InjectionResult addInjectionResult(InjectionResult result){
        return injectionResultRepository.save(result);
    }

    @Override
    public InjectionResult getInjectionResultbyID(int injectionResultID){
        // Add or update new result
        Optional<InjectionResult> result = injectionResultRepository.findById(injectionResultID);
        if(result.isPresent()){
            return result.get();
        }
        throw  new NotFoundException("Could not find any injection result with ID: " + injectionResultID);
    }
    @Override
    public List<InjectionResult>getALLInjectionResult(){
        // show all result
        return injectionResultRepository.findAll();
    }
    @Override
    public void deleteInjectionResultById(int injectionResultID){
        // delete result follow id
        InjectionResult injectionResult = injectionResultRepository.findById(injectionResultID).orElse(null);
        if (injectionResult !=null){
            injectionResult.setStatus(false);
            injectionResultRepository.save(injectionResult);
        }
    }


    @Override
    public InjectionResult updateInjectionResult(int injectionResultID, InjectionResult updatedResult){
        InjectionResult oldInjection = injectionResultRepository.findByInjectionResultID(injectionResultID);
        injectionResultRepository.saveAndFlush(updatedResult);
        return injectionResultRepository.save(updatedResult);
    }
    @Override
    public List<String> CountInjectionResultByYear() {
        return injectionResultRepository.CountInjectionResultByYear();
    }

}
