/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.demo.controller;
import com.example.demo.entity.Cargo;
import com.example.demo.service.CargoService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author bromero
 */
@CrossOrigin(origins = {"*"})
@RestController
@RequestMapping("/nexos")
public class CargoController {
    
    @Autowired
    //Service   
    private CargoService cargoService;

   
    @PostMapping("/cargo")
    public List<Cargo> create(@Valid @RequestBody Cargo cargo,
            BindingResult result) {

        // Instanciamos un nuevo objeto
        Cargo cargoObject = null;

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

        try {
            // Crear el mensaje
            cargoObject = cargoService.save(cargo); // Nuevo mensaje
        } catch (DataAccessException e) {
            response.put("mensaje", "Error al insertar el mensaje en la base de datos");
            response.put("error", e.getMostSpecificCause().getMessage());
            return null;
        }

        response.put("cargo", "Se ha registrado el cargo con éxito!");
        response.put("cargoRegistrado", cargoObject);
        return cargoService.findAll(); // RETURN DEL STATUS CREADO CON EL
    }
    
     @GetMapping("/cargo")
    public List<Cargo> getAllCargos() {
        return cargoService.findAll();
    }
    
    
    
    
}
