package ec.edu.epn;

import ec.edu.epn.visual.PantallaInicial;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        //Es la parte donde inicia el programa. Se llama a la interfaz inicial, y se la hace visible.
        PantallaInicial pantallaInicial = new PantallaInicial();
        pantallaInicial.crearFrame(0);

    }
}