package ec.edu.epn;

import ec.edu.epn.visual.PantallaInicial;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        //Es la parte donde inicia el programa. Se llama a la interfaz inicial, y se la hace visible.
        PantallaInicial pantallaInicial = new PantallaInicial();
        pantallaInicial.crearFrame(0);
        /*
        Diccionario diccionario=new Diccionario();
        if(diccionario.agregarPostura("navasana","boat pose","postura de la barca","nava;asana")){
            JOptionPane.showMessageDialog(null,
                    "Postura agragada correctamente", "Notificación", JOptionPane.INFORMATION_MESSAGE);
        }else{
            JOptionPane.showMessageDialog(null,
                    "Error al agregar postura,\n"+
                            "la postura pude ya existir en el diccionario",
                    "Notificación", JOptionPane.ERROR_MESSAGE);
        }

         */
    }
}