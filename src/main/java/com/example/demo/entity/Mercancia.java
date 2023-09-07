/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.demo.entity;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author bromero
 */

@Entity
@Getter @Setter
@Table(name="mercancia")
public class Mercancia  implements Serializable  {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nombre_producto", unique = true)
    @NotBlank(message = "El nombre del producto es obligatorio")
    private String nombreProducto;

    @NotNull(message = "La cantidad no puede ser nula")
    @PositiveOrZero(message = "La cantidad debe ser un número positivo o cero")
    private Integer cantidad;

    @Temporal(TemporalType.DATE)
    @PastOrPresent(message = "La fecha de ingreso debe ser en el pasado o en el presente")
    private Date fechaIngreso;

    @Temporal(TemporalType.DATE)
    private Date fechaModificacion;

    @ManyToOne
    @JoinColumn(name = "usuario_registro_id")
    @NotNull(message = "El usuario de registro no puede ser nulo")
    private Usuario usuarioRegistro;

    @ManyToOne
    @JoinColumn(name = "usuario_modificacion_id")
    private Usuario usuario;

    
      
}
