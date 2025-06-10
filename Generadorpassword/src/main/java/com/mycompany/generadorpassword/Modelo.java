
package com.mycompany.generadorpassword;
import java.util.Base64;
import java.util.Random;

public class Modelo {
    
        // Método para encriptar una cadena
    public String encriptar(String texto) {
        return Base64.getEncoder().encodeToString(texto.getBytes());
    }
    
    
    // Método para desencriptar una cadena
    public String desencriptar(String textoEncriptado) {
        byte[] decodedBytes = Base64.getDecoder().decode(textoEncriptado);
        return new String(decodedBytes);
    }
    
    
     // Método para generar una contraseña
    public String generarContraseña(int longitud, boolean incluirNumeros, boolean incluirLetras, boolean incluirSimbolos) {
        StringBuilder caracteresPermitidos = new StringBuilder();
        
        if (incluirLetras) {
            caracteresPermitidos.append("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz");
        }
        if (incluirNumeros) {
            caracteresPermitidos.append("0123456789");
        }
        if (incluirSimbolos) {
            caracteresPermitidos.append("!@#$%^&*()-_+=<>?/{}[]|");
        }
        
        if (caracteresPermitidos.length() == 0) {
            return "";  // Si no se selecciona ningún tipo de carácter, retornamos una cadena vacía.
        }
        
        Random rand = new Random();
        StringBuilder contrasena = new StringBuilder();
        
        for (int i = 0; i < longitud; i++) {
            int indice = rand.nextInt(caracteresPermitidos.length());
            contrasena.append(caracteresPermitidos.charAt(indice));
        }
        
        return contrasena.toString();
    }
    
    
    

}
