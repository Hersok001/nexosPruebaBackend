/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.demo.controller;

import com.example.demo.entity.Permiso;
import com.example.demo.service.PermisoService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author bromero
 */

@CrossOrigin(origins = {"*"})
@RestController
@RequestMapping("/nexos")
public class PermisoController {
    
     @Autowired
    //Service   
    private PermisoService permisoService;
     
    @PostMapping("/permiso")
    public ResponseEntity<?> create(@Valid @RequestBody Permiso permiso,
            BindingResult result) {

        // Instanciamos un nuevo objeto
        Permiso permisoObject = null;

        Map<String, Object> response = new HashMap<>();

        // Manejo de errores que vengan del Angular como que los campos no esten vacios
        // y sean del formato correcto
        if (result.hasErrors()) {
            List<String> errors = result.getFieldErrors().stream()
                    .map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
                    .collect(Collectors.toList());

            response.put("errors", errors);
            return null;
        }

        // Flujo normal del insert
        try {
            // Crear el mensaje
            permisoObject = permisoService.save(permiso); // Nuevo mensaje
        } catch (DataAccessException e) {
            response.put("mensaje", "Error al insertar el mensaje en la base de datos");
            response.put("error", e.getMostSpecificCause().getMessage());
            return null;
        }

        response.put("cargo", "Se ha registrado el permiso con éxito!");
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
    
     @GetMapping("/permiso")
    public List<Permiso> getAllCargos() {
        return permisoService.findAll();
    }
    
}
