package com.example.demo;

import com.example.demo.entity.Mercancia;
import com.example.demo.entity.Usuario;
import com.example.demo.utils.enviarCorreo;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class NexosApplicationTests {
    
    @Autowired
    private enviarCorreo sendMail;
  
    
    // prueba unitaria al metodo que envia correos en el aplicativo
    @Test
    void sendCorreoTest(){        
        // Crear un objeto de Mercancia para usar en la prueba
        Mercancia mercancia = new Mercancia();
        Usuario usuario = new Usuario();
        usuario.setCorreo("brayanromero983@gmail.com"); //correo pruebas
        mercancia.setNombreProducto("Producto de prueba");
        mercancia.setUsuario(usuario);
        
        boolean exitoso = sendMail.enviar_correo(mercancia);
        
        // Verificar que el envío haya sido exitoso
        assertTrue(exitoso);                      
    }
    

}
