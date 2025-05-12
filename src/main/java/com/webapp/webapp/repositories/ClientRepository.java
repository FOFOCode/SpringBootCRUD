/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/SpringFramework/Repository.java to edit this template
 */
package com.webapp.webapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.webapp.webapp.models.Client;


/**
 *
 * @author Rodolfo
 */
public interface ClientRepository extends JpaRepository<Client, Integer> {
    
    public Client findByEmail(String email);
    
}
