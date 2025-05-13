/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.webapp.webapp.controllers;


import com.webapp.webapp.models.Client;
import com.webapp.webapp.models.ClientDto;
import com.webapp.webapp.models.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.webapp.webapp.repositories.ClientRepository;
import com.webapp.webapp.repositories.DepartmentRepository;
import jakarta.validation.Valid;
import java.sql.Date;
import org.springframework.data.domain.Sort;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

/**
 *
 * @author Rodolfo
 */

@Controller
@RequestMapping("/clients") // localhost:8080/clients/mapping
public class ClientsController {
    @Autowired
    private ClientRepository clientRepo;
    @Autowired
    private DepartmentRepository departmentRepo;
    
    @GetMapping({"","/"}) // localhost:8080/clients/
    public String getClients(Model model){
        var clients = clientRepo.findAll(Sort.by(Sort.Direction.DESC,"id")); //select * from client
        model.addAttribute("clients", clients);
        
        return "clients/index"; // Templates/clients/index
    }
    
    @GetMapping("/create") 
    public String createClient(Model model){
        ClientDto clientDto = new ClientDto();
        model.addAttribute("clientDto", clientDto);
        model.addAttribute("departments", departmentRepo.findAll()); // Para el select

        return "clients/create";
    }
    
    @PostMapping("/create") //luego de
    public String createClient(@Valid @ModelAttribute ClientDto clientDto, BindingResult result){
        
        if(clientRepo.findByEmail(clientDto.getEmail())!= null){
            result.addError(new FieldError("clientDto", "email", clientDto.getEmail(), false, null, null, "Email address is already used"));
        }
        
        if(result.hasErrors()){
            return "clients/create";
        }
        
        Client client = new Client();
        client.setFirstName(clientDto.getFirstName());
        client.setLastName(clientDto.getLastName());
        client.setEmail(clientDto.getEmail());
        client.setPhone(clientDto.getPhone());
        client.setAddress(clientDto.getAddress());
        client.setStatus(clientDto.getStatus());
        client.setCreatedAt(new Date(System.currentTimeMillis()));
        
        
        // Buscar departamento por ID
        Department department = departmentRepo.findById(clientDto.getDepartmentId()).orElse(null);
        client.setDepartment(department);
        
        clientRepo.save(client);
        
        return "redirect:/clients";
    }
    
    @GetMapping("/edit")
    public String editClient(Model model, @RequestParam int id){
        Client client = clientRepo.findById(id).orElse(null);
        if(client == null){
            return "redirect:/clients";
        }
        
        ClientDto clientDto = new ClientDto();
        clientDto.setFirstName(client.getFirstName());
        clientDto.setLastName(client.getLastName());
        clientDto.setEmail(client.getEmail());
        clientDto.setPhone(client.getPhone());
        clientDto.setAddress(client.getAddress());
        clientDto.setStatus(client.getStatus());
        clientDto.setDepartmentId(client.getDepartment().getId()); // Nuevo

        model.addAttribute("client", client);
        model.addAttribute("clientDto", clientDto);
        model.addAttribute("departments", departmentRepo.findAll()); // Para el select
        
        return "clients/edit"; // Templates/clients/edit
    }
    
    @PostMapping("/edit") // luego de
    public String editClient(Model model, @RequestParam int id, @Valid @ModelAttribute ClientDto clientDto, BindingResult result){
        
        Client client = clientRepo.findById(id).orElse(null);
        if(client == null){
            return "redirect:/clients";
        }
        
        model.addAttribute("client", client);
        
        if(result.hasErrors()){
            return "clients/edit";
        }
        
        //update client details
        client.setFirstName(clientDto.getFirstName());
        client.setLastName(clientDto.getLastName());
        client.setEmail(clientDto.getEmail());
        client.setPhone(clientDto.getPhone());
        client.setAddress(clientDto.getAddress());
        client.setStatus(clientDto.getStatus());
        
        // Asignar departamento actualizado
        Department department = departmentRepo.findById(clientDto.getDepartmentId()).orElse(null);
        client.setDepartment(department);
        
        try {
            clientRepo.save(client);
        } catch(Exception ex){
            result.addError(new FieldError("clientDto", "email", clientDto.getEmail(), false,null,null, "Email address is already used"));
            return "clients/edit";
        }
        
        
        return "redirect:/clients";
    }
    
    @GetMapping("/delete")
    public String deleteClient(@RequestParam int id){
        Client client = clientRepo.findById(id).orElse(null);
        
        if(client != null){
            clientRepo.delete(client);
        }
        
        return "redirect:/clients";
    }
}
