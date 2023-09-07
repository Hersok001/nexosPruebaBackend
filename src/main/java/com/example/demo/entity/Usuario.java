/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.demo.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;

/**
 *
 * @author bromero
 */

@Entity
@Getter
@Setter
@Table(name = "usuarios")
public class Usuario implements Serializable  {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    
    @NotBlank(message = "El nombre es obligatorio")
    @Size(max = 255, message = "El nombre no debe superar los 255 caracteres")
    @Column(name = "nombre")
    private String nombre; 

    @NotNull(message = "La edad es obligatoria")
    @Min(value = 18, message = "La edad mínima permitida es 18")
    @Max(value = 150, message = "La edad máxima permitida es 150") // Ajusta según tus necesidades
    @Column(name = "edad")
    private Integer edad;

    @NotNull(message = "La fecha de ingreso es obligatoria")
    @PastOrPresent(message = "La fecha de ingreso debe ser en el pasado o en el presente")
    @Column(name = "fecha_ingreso")
    @Temporal(TemporalType.DATE)
    private Date fechaIngreso;

    @ManyToOne
    private Cargo cargo;

    @ManyToOne
    private Permiso permiso;
    
    @Column(name = "correo")
    private String correo; 

   private static final long serialVersionUID = 1L;
    
}
