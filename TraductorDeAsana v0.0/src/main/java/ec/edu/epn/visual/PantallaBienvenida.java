package ec.edu.epn.visual;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PantallaBienvenida extends JFrame {
    private JPanel panelPrincipal;
    private JButton traducirPosturaButton;
    private JButton traducirMorfemaButton;
    private JButton regresarButton;

    public PantallaBienvenida() {
        this.colocarIconos("src/main/java/ec/edu/epn/visual/imagenes/iconoRegresar.png",regresarButton);
        this.colocarIconos("src/main/java/ec/edu/epn/visual/imagenes/iconoPostura.png",traducirPosturaButton);
        this.colocarIconos("src/main/java/ec/edu/epn/visual/imagenes/iconoMorfema.png",traducirMorfemaButton);



        traducirPosturaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PantallaTraduccionPostura pantallaTraduccionPostura = new PantallaTraduccionPostura();
                pantallaTraduccionPostura.crearFrame();
                dispose();
            }
        });
        traducirMorfemaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PantallaTraduccionMorfema pantallaTraduccionMorfema = new PantallaTraduccionMorfema();
                pantallaTraduccionMorfema.crearFrame();
                dispose();
            }
        });
        regresarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PantallaInicial pantallaInicial = new PantallaInicial();
                pantallaInicial.crearFrame();
                dispose();
            }
        });
    }

    public void crearFrame() {
        setSize(600, 400);
        setTitle("Bienvenida usuario");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        add(panelPrincipal);
        setVisible(true);
    }

    public void colocarIconos(String direccion, JButton boton){
        ImageIcon iconoNuevo = new ImageIcon(direccion);
        Image scaledIcon = iconoNuevo.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        boton.setIcon(new ImageIcon(scaledIcon));
        boton.setPreferredSize(new Dimension(50, 50));
        boton.setIconTextGap(10);
    }


}
