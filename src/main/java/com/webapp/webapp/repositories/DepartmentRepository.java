/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.webapp.webapp.repositories;

import com.webapp.webapp.models.Department;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Rodolfo
 */
public interface DepartmentRepository extends JpaRepository<Department, Integer>{
    
}
