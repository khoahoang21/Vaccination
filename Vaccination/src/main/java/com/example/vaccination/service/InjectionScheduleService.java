package com.example.vaccination.service;

import com.example.vaccination.model.entity.InjectionSchedule;

import java.util.List;

public interface InjectionScheduleService {

    List<InjectionSchedule> findAll();

    List<InjectionSchedule> findAllByOrderByInjectionScheduleID();

    InjectionSchedule save(InjectionSchedule injectionSchedule);

    InjectionSchedule get(int injectrionScheduleID);


}
