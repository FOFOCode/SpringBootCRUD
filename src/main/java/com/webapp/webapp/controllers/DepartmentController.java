/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.webapp.webapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.webapp.webapp.repositories.DepartmentRepository;
import org.springframework.data.domain.Sort;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author Rodolfo
 */

@Controller
@RequestMapping("/department")
public class DepartmentController {
    
    @Autowired
    private DepartmentRepository departmentRepo;
    
    @GetMapping({"","/"})
    public String getDepartments(Model model){
        var departments = this.departmentRepo.findAll(Sort.by(Sort.Direction.DESC, "id"));
        model.addAttribute("departments", departments);
        
        return "departments/index";
    }
}
