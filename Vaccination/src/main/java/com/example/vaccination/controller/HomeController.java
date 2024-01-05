package com.example.vaccination.controller;

import com.example.vaccination.model.entity.News;
import com.example.vaccination.service.NewsServices;
import com.example.vaccination.service.impl.InjectionResultServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;


@Controller
public class HomeController {

    @Autowired
    NewsServices newsServices;


    @Autowired
    private InjectionResultServiceImpl injectionResultService;

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_EMPLOYEE')")
    @GetMapping(value = {"/", "/home"})
    public String showHomepage(Model model) {
        List<String> injectionResultList = injectionResultService.CountInjectionResultByYear();
        model.addAttribute("injectionResultList",injectionResultList);
        List<News> newsList = newsServices.findTop5ByOrderByPostdateDesc();
        model.addAttribute("newsList", newsList);
        return "homePage";
    }
    @GetMapping(value = {"/access-denied"})
    public String accessDenied(Model model) {
        return "access-denied";
    }

}