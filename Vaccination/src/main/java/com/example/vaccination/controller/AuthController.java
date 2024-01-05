package com.example.vaccination.controller;

import com.example.vaccination.model.entity.Employee;
import com.example.vaccination.service.impl.AuthenticationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auths")
public class AuthController {

    @Autowired
    private AuthenticationServiceImpl service;

    @GetMapping("/reset/find")
    public ResponseEntity<Employee> restFind(@RequestParam("email") String email) {
        return ResponseEntity.ok().body(service.resetFind(email));
    }
//
//    @PostMapping("/reset/send")
//    public ResponseEntity<ResetResponse> resetSend(@RequestParam("email") String email) throws Exception {
//        return ResponseEntity.ok().body(service.resetSend(email));
//    }

    @PostMapping("/reset/confirm")
    public ResponseEntity<ResetResponse> resetConfirm(@RequestParam("email") String email, @RequestParam("code") String code) {
        return ResponseEntity.ok().body(service.resetConfirm(email, code));
    }
//
//    @PostMapping("/reset/new")
//    public ResponseEntity<ResetResponse> resetNew(@RequestParam("email") String email, @RequestParam("password") String password) {
//        return ResponseEntity.ok().body(service.resetNew(email, password));
//    }


}