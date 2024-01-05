package com.example.vaccination.service.impl;

import com.example.vaccination.controller.ResetResponse;
import com.example.vaccination.mail.EmailSender;
import com.example.vaccination.mail.ForgetCodeTemplate;
import com.example.vaccination.model.entity.Employee;
import com.example.vaccination.token.Token;
import com.example.vaccination.token.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.List;

import static java.util.regex.Pattern.matches;

@Service
public class AuthenticationServiceImpl {

    @Autowired
    private EmailSender gmailSender;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private EmployeeServiceImpl employeeService;

    public Employee resetFind(@RequestParam("email") String email){
        return employeeService.findByEmail(email);
    }

    public ResetResponse resetSend(String email) throws Exception {
        Employee exist = employeeService.findByEmail(email);
        Token checkToken = tokenService.findByEmp(exist);
        if (checkToken != null) {
            tokenService.delete(checkToken);
        }
        String forgetCode = Math.round((Math.random() * 899999 + 100000)) + "";
        System.out.println("Forget Code: " + forgetCode);
        String tokens = forgetCode;
        Token resetToken = Token.builder()
                .createAt(LocalDateTime.now())
                .expiredAt(LocalDateTime.now().plusSeconds(20))
                .value(tokens)
                .employee(exist)
                .build();
        tokenService.save(resetToken);
        String template = ForgetCodeTemplate.getTemplete(exist.getUsername(), forgetCode);
        gmailSender.send(template,exist.getEmail());
        return ResetResponse.builder()
                .email(exist.getEmail())
                .status("Send confirm code succesfully")
                .build();
    }

    public ResetResponse resetConfirm(String email, String code) {
        String status = "Confirm Succesfully";
        Employee exist = employeeService.findByEmail(email);
        Token resetToken = tokenService.findByEmp(exist);
        boolean tmp = true;
        if (!tokenService.isValid(resetToken)) {
            tmp = false;
            status = "Confirm code is expired!";
        }
        boolean isMatching = passwordEncoder.matches(code, resetToken.getValue());
        if (!isMatching) {
            status = "Code isn't match";
        }
        System.out.println(tmp);
        return ResetResponse.builder()
                .email(exist.getEmail())
                .status(status)
                .build();
    }

    public ResetResponse resetNew(String email, String newPass) {
        String status = "Reset Password Succesfully";
        Employee exist = employeeService.findByEmail(email);
        employeeService.saving(email, newPass);
        return ResetResponse.builder()
                .email(exist.getEmail())
                .status(status)
                .build();
    }
}
