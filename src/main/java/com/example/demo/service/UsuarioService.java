/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.demo.service;

import com.example.demo.entity.Usuario;
import java.util.List;

/**
 *
 * @author bromero
 */
public interface UsuarioService {
        
    public Usuario findById(Integer Id);
    
    public List<Usuario> findAll();   //Metodo mostrar todos

    public Usuario save(Usuario usuario);   //Insert

    public void delete(Integer id);   //delete 
    
}
