/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.example.demo.dao;

import com.example.demo.entity.Permiso;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author bromero
 */
public interface PermisoDao extends CrudRepository<Permiso, Integer>  {
    
}
