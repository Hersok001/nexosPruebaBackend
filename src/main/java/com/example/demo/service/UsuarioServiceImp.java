/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.demo.service;

import com.example.demo.dao.UsuarioDao;
import com.example.demo.entity.Usuario;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author bromero
 */
@Service
public class UsuarioServiceImp implements UsuarioService {
    
    
    @Autowired // Inyeccion dependencias
    private UsuarioDao dao;
    
    @Override
    @Transactional(readOnly=true)
    public List<Usuario> findAll() {
        return (List<Usuario>) dao.findAll();
    }

    @Override
    @Transactional
    public Usuario save(Usuario usuario) {
        return dao.save(usuario);
    }

   @Transactional
    public void delete(Integer id) {
         dao.deleteById(id);
    }

    @Override
    public Usuario findById(Integer Id) {
        return dao.findById(Id).get();
    }
    
}
