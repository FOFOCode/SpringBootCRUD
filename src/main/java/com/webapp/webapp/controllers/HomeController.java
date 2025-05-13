/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.webapp.webapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author Rodolfo
 */
@Controller
public class HomeController {
    
    @GetMapping("/") // localhost:8080/
    public String home(){
        return "index"; // Templates(vistas)/index
    }
    
}
