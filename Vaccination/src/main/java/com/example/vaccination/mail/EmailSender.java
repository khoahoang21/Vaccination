/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.example.vaccination.mail;

public interface EmailSender {
    public void send(String message, String targetEmail) throws Exception;
}