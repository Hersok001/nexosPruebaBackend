/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.demo.utils;

import com.example.demo.entity.Mercancia;

import javax.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

/**
 *
 * @author bromero
 */
@Service
public class enviarCorreo {

    @Autowired
    public JavaMailSender mail;

    /**
     * Este metodo se encarga de enviar los correos del aplicativo
     *
     * @param mercancia Object gestionado
     * @return 
     */
    public boolean enviar_correo(Mercancia mercancia) {

        MimeMessage mimeMessage = mail.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");

        try {
            String cuerpoCorreo = "Hola: "+ mercancia.getUsuario().getNombre()+" Se ha gestionado el producto: " + mercancia.getNombreProducto(); // Cuerpo del correo
            //Se asignan los parametros al correo

            helper.setText(cuerpoCorreo, true);          //cuerpo correo
            helper.setTo(mercancia.getUsuario().getCorreo()); //destinatario
            helper.setSubject("Prueba Nexos");		 //Encabezado
            helper.setFrom("nexosprueba7@gmail.com");        //remitente
        } catch (Exception e) {    
            e.printStackTrace();
            return false;
        }   
        mail.send(mimeMessage);
        return true; //envio exitoso
    }
}
