
package com.mycompany.generadorpassword;

import java.util.Random;

public class Controlador {
    private Modelo modelo;
    private Vista vista;

    // Constructor que recibe el modelo y la vista
    public Controlador(Modelo modelo, Vista vista) {
        this.modelo = modelo;
        this.vista = vista;
    }

    // Método para encriptar el texto ingresado
    public void encriptarTexto() {
        String texto = vista.getTxtCadena(); // Obtener el texto de la vista
        if (!texto.isEmpty()) {
            String textoEncriptado = modelo.encriptar(texto); // Encriptar con el modelo
            vista.setTxtResultado(textoEncriptado); // Mostrar el resultado en la vista
        }
    }

    // Método para desencriptar el texto ingresado
    public void desencriptarTexto() {
        String textoEncriptado = vista.getTxtResultado(); // Obtener el texto encriptado
        if (!textoEncriptado.isEmpty()) {
            String textoDesencriptado = modelo.desencriptar(textoEncriptado); // Desencriptar con el modelo
            vista.setTxtResultado(textoDesencriptado); // Mostrar el resultado en la vista
        }
    }

    // Método para generar la contraseña
    public void generarContraseña() {
        // Obtener la longitud deseada desde el campo de texto
        int longitud = Integer.parseInt(vista.getLongitudContraseña()); // Acceder al getter de Vista
        if (longitud < 8 || longitud > 12) {
            vista.setTxtResultadoContraseñaGenerada("Longitud fuera de rango (debe ser entre 8 y 12)");
            return;
        }

        // Obtener las opciones de los checkboxes
        boolean incluirNumeros = vista.isIncluirNumeros();
        boolean incluirLetras = vista.isIncluirLetras();
        boolean incluirSimbolos = vista.isIncluirSimbolos();

        // Generar la contraseña con base en las opciones seleccionadas
        String contraseñaGenerada = generarContraseñaAleatoria(longitud, incluirNumeros, incluirLetras, incluirSimbolos);

        // Mostrar la contraseña generada en el campo jTextField4
        vista.setTxtResultadoContraseñaGenerada(contraseñaGenerada);
    }

    // Método auxiliar para generar la contraseña aleatoria
    private String generarContraseñaAleatoria(int longitud, boolean incluirNumeros, boolean incluirLetras, boolean incluirSimbolos) {
        StringBuilder contraseña = new StringBuilder();
        Random random = new Random();

        String caracteresLetras = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String caracteresNumeros = "0123456789";
        String caracteresSimbolos = "!@#$%^&*()-_=+[]{}|;:,.<>?/";

        StringBuilder caracteresDisponibles = new StringBuilder(caracteresLetras); // Incluir letras siempre

        if (incluirNumeros) {
            caracteresDisponibles.append(caracteresNumeros); // Incluir números si se selecciona
        }
        if (incluirSimbolos) {
            caracteresDisponibles.append(caracteresSimbolos); // Incluir símbolos si se selecciona
        }

        // Generar la contraseña de longitud especificada
        for (int i = 0; i < longitud; i++) {
            int indiceAleatorio = random.nextInt(caracteresDisponibles.length());
            contraseña.append(caracteresDisponibles.charAt(indiceAleatorio));
        }

        return contraseña.toString();
    }
}
