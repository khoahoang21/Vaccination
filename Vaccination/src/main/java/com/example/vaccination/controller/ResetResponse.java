package com.example.vaccination.controller;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class ResetResponse {
    private String email;
    private String status;
}