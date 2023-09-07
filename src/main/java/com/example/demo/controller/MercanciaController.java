/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.demo.controller;

import com.example.demo.entity.Mercancia;
import com.example.demo.entity.Usuario;
import com.example.demo.service.MercanciaService;
import com.example.demo.service.UsuarioService;
import com.example.demo.utils.enviarCorreo;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import static java.lang.Math.log;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

/**
 *
 * @author bromero
 */
@CrossOrigin(origins = {"*"})
@RestController
@RequestMapping("/nexos")
public class MercanciaController {
    
     private static Log log = LogFactory.getLog(MercanciaController.class);

    @Autowired
    //Service   
    private MercanciaService mercanciaService;
    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private enviarCorreo sendMail;

    @PostMapping("/mercancia")
    public ResponseEntity<?> create(@Valid @RequestBody Mercancia mercancia,
            BindingResult result) {

        // Instanciamos un nuevo objeto
        Mercancia mercanciaObject = null;

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
            // Obtener el usuario relacionado con la mercancía
            Usuario usuario = usuarioService.findById(mercancia.getUsuario().getId());

            if (usuario != null) {
                mercancia.setUsuario(usuario);
            }

            mercanciaObject = mercanciaService.save(mercancia);

            // envia correo informando la novedad con un hilo
            EnvioCorreoHilo sendMailHilo = new EnvioCorreoHilo(mercanciaObject);
            sendMailHilo.start();

        } catch (DataAccessException e) {
            response.put("mensaje", "Error al insertar el mensaje en la base de datos");
            response.put("error", e.getMostSpecificCause().getMessage());
            response.put("status", 400);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        response.put("mercancia", "Se ha gestionado la mercancia!");
        response.put("status", 201);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/mercancia")
    public List<Mercancia> getAllCargos() {
        return mercanciaService.findAll();
    }

    @DeleteMapping("/mercancia/{id}")
    public ResponseEntity<String> deleteObjeto(@PathVariable Integer id) {
        try {
            mercanciaService.delete(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("El objeto con ID " + id + " ha sido eliminado exitosamente.");
        } catch (EmptyResultDataAccessException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El objeto con ID " + id + " no se encontró y no pudo ser eliminado.");
        }
    }

    /**
     * Este metodo exporta la lista de mercancia
     *
     * @return
     * @throws IOException
     */
    @GetMapping("/mercancia/exportar_excel")
    public ResponseEntity<byte[]> exportToExcel() throws IOException {

        try {
            List<Mercancia> mercanciaList = mercanciaService.findAll();

            // Crear un libro de trabajo Excel
            Workbook workbook = new XSSFWorkbook();
            Sheet sheet = workbook.createSheet("mercancia_prueba_nexos");

            // Crear una fila para las cabeceras
            Row headerRow = sheet.createRow(0);
            headerRow.createCell(0).setCellValue("Mercancia");
            headerRow.createCell(1).setCellValue("Cantidad");

            // Llenar el contenido
            int rowNum = 1;
            for (Mercancia mercancia : mercanciaList) {
                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(mercancia.getNombreProducto());
                row.createCell(1).setCellValue(mercancia.getCantidad());
            }

            // Convertir el libro de trabajo a un flujo de bytes
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            workbook.write(outputStream);
            outputStream.close();

            // Configurar la respuesta HTTP para descargar el archivo Excel
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            headers.setContentDispositionFormData("attachment", "mercancia_prueba_nexos.xlsx");

            return new ResponseEntity<>(outputStream.toByteArray(), headers, org.springframework.http.HttpStatus.OK);

        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //Hilo para enviar los correos
    public class EnvioCorreoHilo extends Thread {

        private Mercancia mercancia;

        public EnvioCorreoHilo(Mercancia mercancia) {
            this.mercancia = mercancia;
        }

        @Override
        public void run() {     
            try {
                sendMail.enviar_correo(mercancia);
            } catch (Exception e) {
                log.error("Error en el hilo de envio de correos" + e);
            } finally {
                
            }
        }
    }
    
    @GetMapping("/mercancia/{id}")
    public Mercancia getById(@PathVariable Integer id) {
        return mercanciaService.findById(id);
    }
}
