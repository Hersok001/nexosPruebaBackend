/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.demo.service;

import com.example.demo.dao.PermisoDao;
import com.example.demo.entity.Permiso;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author bromero
 */
@Service
public class PermisoImp implements PermisoService{
    
    @Autowired  // Inyeccion dependencias
    private PermisoDao dao;
    
    @Override
    @Transactional(readOnly=true)
    public List<Permiso> findAll() {
        return (List<Permiso>) dao.findAll();
    }

    @Override
    @Transactional
    public Permiso save(Permiso permiso) {
        return dao.save(permiso);
    }

   @Transactional
    public void delete(Integer id) {
         dao.deleteById(id);
    }        
}
