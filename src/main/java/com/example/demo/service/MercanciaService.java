/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.example.demo.service;

import com.example.demo.entity.Mercancia;
import java.util.List;

/**
 *
 * @author bromero
 */
public interface MercanciaService {

    public List<Mercancia> findAll();   //Metodo mostrar todos

    public Mercancia save(Mercancia mercancia);   //Insert

    public void delete(Integer id);   //delete 
    
    public Mercancia findById(Integer id);
   
}
