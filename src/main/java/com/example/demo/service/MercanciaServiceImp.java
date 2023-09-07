/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.demo.service;

import com.example.demo.dao.MercanciaDao;
import com.example.demo.entity.Mercancia;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author bromero
 */
@Service
public class MercanciaServiceImp implements MercanciaService {
    
    
    @Autowired  // Inyeccion dependencias
    private MercanciaDao dao;
    

    @Override
    @Transactional(readOnly=true)
    public List<Mercancia> findAll() {
        return (List<Mercancia>) dao.findAll();
    }

    @Override
    @Transactional
    public Mercancia save(Mercancia mercancia) {
        return dao.save(mercancia);
    }

   @Transactional
    @Override
    public void delete(Integer id) {
         dao.deleteById(id);
    }   

    @Override
    @Transactional(readOnly=true)
    public Mercancia findById(Integer id) {
        return dao.findById(id).get();
    }
   
}
