package com.example.vaccination.controller;

import com.example.vaccination.exception.ApplicationException;
import com.example.vaccination.mail.EmailSender;
import com.example.vaccination.mail.ForgetCodeTemplate;
import com.example.vaccination.model.entity.Employee;
import com.example.vaccination.service.impl.AuthenticationServiceImpl;
import com.example.vaccination.service.impl.EmployeeServiceImpl;
import com.example.vaccination.token.Token;
import com.example.vaccination.token.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;

import static java.util.regex.Pattern.matches;

@Controller
public class ForgotPasswordController {

    @Autowired
    private EmailSender gmailSender;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationServiceImpl service;

    @Autowired
    private EmployeeServiceImpl employeeService;

    @GetMapping("/forgot_password_email")
    public String forgotPasswordForm() {
        return "forgot_password";
    }

    @PostMapping("/forgot_password_form")
    public String proccesingPassword(@RequestParam(value = "email") String email, Model model) {
        try {
            Employee exist = employeeService.findByEmail(email);
            Token checkToken = tokenService.findByEmp(exist);
            if (checkToken != null){
                tokenService.delete(checkToken);
            }
            if (exist != null) {

                String forgetCode = Math.round((Math.random() * 899999 + 100000)) + "";
                System.out.println("Forget Code: " + forgetCode);
                String tokens = passwordEncoder.encode(forgetCode);
                Thread emailThread = new Thread(() -> {
                    Token resetToken = Token.builder()
                            .createAt(LocalDateTime.now())
                            .expiredAt(LocalDateTime.now().plusSeconds(80))
                            .value(tokens)
                            .employee(exist)
                            .build();
                    tokenService.save(resetToken);
                    String template = ForgetCodeTemplate.getTemplete(exist.getUsername(), forgetCode);
                    try {
                        gmailSender.send(template,exist.getEmail());
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                });
                emailThread.start();
                model.addAttribute("email", email);
                model.addAttribute("forgetCode", tokens);
                return "forgot_password_form";
            } else {
                model.addAttribute("error", "Email does not exist!!!");
                return "forgot_password";
            }
        } catch (Exception e) {
            model.addAttribute("error", "An error occurred.");
            return "forgot_password";
        }
    }

    @GetMapping(path = "/resend_code")
    public String resendCode(@RequestParam(value = "email") String email, Model model){
        try {
            Employee exist = employeeService.findByEmail(email);
            Token checkToken = tokenService.findByEmp(exist);
            if (checkToken != null){
                tokenService.delete(checkToken);
            }
            if (exist != null) {
                String forgetCode = Math.round((Math.random() * 899999 + 100000)) + "";
                System.out.println("Forget Code: " + forgetCode);
                String tokens = passwordEncoder.encode(forgetCode);
                Thread emailThread = new Thread(() -> {
                    Token resetToken = Token.builder()
                            .createAt(LocalDateTime.now())
                            .expiredAt(LocalDateTime.now().plusSeconds(80))
                            .value(tokens)
                            .employee(exist)
                            .build();
                    tokenService.save(resetToken);
                    String template = ForgetCodeTemplate.getTemplete(exist.getUsername(), forgetCode);
                    try {
                        gmailSender.send(template,exist.getEmail());
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                });
                emailThread.start();
                model.addAttribute("email", email);
                model.addAttribute("forgetCode", tokens);
                return "forgot_password_form";
            } else {
                model.addAttribute("error", "Email does not exist!!!");
                return "forgot_password";
            }
        } catch (Exception e) {
            model.addAttribute("error", "An error occurred.");
            return "forgot_password";
        }
    }

    @PostMapping("/reset/confirm")
    public ResponseEntity<ResetResponse> resetConfirm(@RequestParam("email") String email, @RequestParam("code") String code) {
        return ResponseEntity.ok().body(service.resetConfirm(email, code));
    }

    @GetMapping(path ="/resetNew")
    public String resetpass(@RequestParam(value = "email") String email,
                            @RequestParam(value = "code") String code, Model model){
        model.addAttribute("email", email);
        model.addAttribute("code", code);
        Employee exist = employeeService.findByEmail(email);
        Token checkToken = tokenService.findByEmp(exist);
        if (!tokenService.isValid(checkToken)){
            tokenService.delete(checkToken);
            return "redirect:/login";
        }
        if (code.equals(checkToken.getValue())){
            return "change-password";
        }
        return "access-denied";
    }

    @PostMapping(path ="/resetNew")
    public String resetPassword(@RequestParam(value = "email") String email,
                                @RequestParam String code,
                                @RequestParam(value = "password") String newPass){
        Employee exist = employeeService.findByEmail(email);
        Token checkToken = tokenService.findByEmp(exist);
        if (tokenService.isValid(checkToken) && code.equals(checkToken.getValue())) {
            if (newPass != null) {
                employeeService.saving(email, newPass);
                tokenService.delete(checkToken);
                return "redirect:/login";
            }
        } else {

            tokenService.delete(checkToken);
            throw new ApplicationException("Time out!!!");
        }
        return "access-denied";
    }
}
