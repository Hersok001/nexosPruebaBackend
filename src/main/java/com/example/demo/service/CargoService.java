/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.demo.service;

import com.example.demo.entity.Cargo;
import java.util.List;

/**
 *
 * @author bromero
 */
public interface CargoService {
    
    public List<Cargo> findAll();   //Metodo mostrar todos
	
	public Cargo save(Cargo cargo);   //Insert
	
	public void delete(Integer id);   //delete 
	
        	
}
