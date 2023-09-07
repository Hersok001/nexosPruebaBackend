/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.demo.service;
import com.example.demo.dao.CargoDao;
import com.example.demo.entity.Cargo;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author bromero
 */
@Service
public class CargoServiceImp implements CargoService {
    
    @Autowired  // Inyeccion dependencias
    private CargoDao dao;
    
    @Override
    @Transactional(readOnly=true)
    public List<Cargo> findAll() {
        return (List<Cargo>) dao.findAll();
    }

    @Override
    @Transactional
    public Cargo save(Cargo cargo) {
        return dao.save(cargo);
    }

   @Transactional
    public void delete(Integer id) {
         dao.deleteById(id);
    }
    
}
