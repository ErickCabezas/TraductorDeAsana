package ec.edu.epn.visual;

import ec.edu.epn.Diccionario;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PantallaTraduccionPostura extends JFrame {
    private JPanel panelPrincipal;
    private JTextField posturaTextField;
    private JTextPane panelTraduccion;
    private JButton traducirButton;
    private JButton regresarButton;

    public PantallaTraduccionPostura() {
        this.colocarIconos("src/main/java/ec/edu/epn/visual/imagenes/iconoTraducir.png",traducirButton);
        this.colocarIconos("src/main/java/ec/edu/epn/visual/imagenes/iconoRegresar.png",regresarButton);

        Diccionario diccionario = new Diccionario();
        traducirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String postura = posturaTextField.getText();
                if(postura.equalsIgnoreCase("")){
                    JOptionPane.showMessageDialog(null, "Error #1\nNo ha ingresado ningúna postura.\nPor favor, escriba una.", "!Algo salió mal!", JOptionPane.ERROR_MESSAGE);
                }else{
                    String traduccion = diccionario.buscarPostura(postura);
                    panelTraduccion.setText(traduccion);
                }
            }
        });
        regresarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PantallaBienvenida pantallaBienvenida = new PantallaBienvenida();
                pantallaBienvenida.crearFrame();
                dispose();
            }
        });
    }

    public void crearFrame() {
        setSize(600, 500);
        setTitle("Traductor de posturas");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        add(panelPrincipal);
        setVisible(true);
    }

    public void colocarIconos(String direccion, JButton boton){
        ImageIcon iconoNuevo = new ImageIcon(direccion);
        Image scaledIcon = iconoNuevo.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        boton.setIcon(new ImageIcon(scaledIcon));
        boton.setPreferredSize(new Dimension(50, 50));
        boton.setIconTextGap(10);
    }

}
