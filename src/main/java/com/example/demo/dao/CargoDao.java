/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.demo.dao;
import com.example.demo.entity.Cargo;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author bromero
 */

public interface CargoDao extends CrudRepository<Cargo, Integer> {
}

