package ec.edu.epn.visual;

import ec.edu.epn.Diccionario;
import ec.edu.epn.Imagen;
import ec.edu.epn.Morfema;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class PantallaTraduccionMorfema extends JFrame {
    private JPanel panelPrincipal;
    private JTextField morfemaTextField;
    private JButton traducirButton;
    private JTextPane panelTraduccion;
    private JButton regresarButton;
    private JPanel panelImagen;


    public PantallaTraduccionMorfema() {
        this.colocarIconos("src/main/java/ec/edu/epn/visual/imagenes/iconos/iconoTraducir.png",traducirButton);
        this.colocarIconos("src/main/java/ec/edu/epn/visual/imagenes/iconos/iconoRegresar.png",regresarButton);
        Diccionario diccionario = new Diccionario();
        panelImagen.add(retornarImagenMorfema("holaMorfema.gif"));
        traducirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String morfemaBuscado = morfemaTextField.getText();
                if(morfemaBuscado.equalsIgnoreCase("")){
                    panelTraduccion.setText("");
                    panelImagen.removeAll();
                    panelImagen.add(retornarImagenMorfema("holaMorfema.gif"));
                    JOptionPane.showMessageDialog(null, "Error #2\nNo ha ingresado ningún morféma.\nPor favor, escriba una.", "!Algo salió mal!", JOptionPane.ERROR_MESSAGE);
                }else{
                    Morfema morfema = diccionario.buscarMorfema(morfemaBuscado);
                    if(morfema==null){
                        panelTraduccion.setText("No hallamos ese morfema.");
                        panelImagen.removeAll();
                        panelImagen.add(retornarImagenMorfema("noHayMorfema.gif"));
                    }else{
                        panelTraduccion.setText(morfema.toString());
                        panelImagen.removeAll();
                        panelImagen.add(retornarImagenMorfema(morfema.getEspañol()+".gif"));
                    }
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


        morfemaTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char caracter = e.getKeyChar();
                if(Character.isDigit(caracter)){
                    JOptionPane.showMessageDialog(null, "Por favor, no ingrese números.", "Advertencia", JOptionPane.WARNING_MESSAGE);
                    e.consume();
                }
            }
        });
    }

    public void crearFrame() {
        setSize(490, 470);
        setTitle("Traductor");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        add(panelPrincipal);
        setVisible(true);
        Image image = Toolkit.getDefaultToolkit().getImage("src/main/java/ec/edu/epn/visual/imagenes/iconos/iconoVentana.png");
        setIconImage(image);
    }

    public void colocarIconos(String direccion, JButton boton){
        ImageIcon iconoNuevo = new ImageIcon(direccion);
        Image scaledIcon = iconoNuevo.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        boton.setIcon(new ImageIcon(scaledIcon));
        boton.setPreferredSize(new Dimension(50, 50));
        boton.setIconTextGap(10);

    }

    public JLabel retornarImagenMorfema(String nombreImagen){
        ImageIcon imagen = new ImageIcon("src/main/java/ec/edu/epn/visual/imagenes/morfemas/"+nombreImagen);
        Image scaledIcon = imagen.getImage().getScaledInstance(260, 200, Image.SCALE_DEFAULT);
        JLabel label = new JLabel(new ImageIcon(scaledIcon));
        return label;
    }

}
