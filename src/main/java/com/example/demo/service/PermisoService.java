/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.example.demo.service;

import com.example.demo.entity.Permiso;
import java.util.List;

/**
 *
 * @author bromero
 */
public interface PermisoService {

    public List<Permiso> findAll();   //Metodo mostrar todos

    public Permiso save(Permiso permiso);   //Insert

    public void delete(Integer id);   //delete 

}
