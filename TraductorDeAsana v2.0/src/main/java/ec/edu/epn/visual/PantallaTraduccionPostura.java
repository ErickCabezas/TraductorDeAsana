package ec.edu.epn.visual;

import ec.edu.epn.Diccionario;
import ec.edu.epn.PosturaAsana;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class PantallaTraduccionPostura extends JFrame {
    private JPanel panelPrincipal;
    private JTextField posturaTextField;
    private JTextPane panelTraduccion;
    private JButton traducirButton;
    private JButton regresarButton;
    private JPanel panelImagen;
    private JComboBox comboBox;

    public PantallaTraduccionPostura() {
        this.colocarIconos("src/main/java/ec/edu/epn/visual/imagenes/iconos/iconoTraducir.png",traducirButton);
        this.colocarIconos("src/main/java/ec/edu/epn/visual/imagenes/iconos/iconoRegresar.png",regresarButton);
        Diccionario diccionario = new Diccionario();
        panelImagen.add(retornarImagenPostura("inicioPostura.gif"));

        traducirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String posturaBuscada = posturaTextField.getText();
                if(posturaBuscada.equalsIgnoreCase("")){
                    panelTraduccion.setText("");
                    panelImagen.removeAll();
                    panelImagen.add(retornarImagenPostura("inicioPostura.gif"));

                    JOptionPane.showMessageDialog(null, "Error #1\nNo ha ingresado ningúna postura.\nPor favor, escriba una.", "!Algo salió mal!", JOptionPane.ERROR_MESSAGE);
                }else{
                    int tipoTraduccion = comboBox.getSelectedIndex();
                    PosturaAsana postura = null;
                    if(tipoTraduccion==0){
                        postura = diccionario.buscarPosturaSanscrito(posturaBuscada);
                    }else if(tipoTraduccion==1){
                        postura = diccionario.buscarPosturaEspaniol(posturaBuscada);
                    }else if(tipoTraduccion==2){
                        postura = diccionario.buscarPosturaIngles(posturaBuscada);
                    }

                    if(postura == null){
                        panelTraduccion.setText("No hallamos esa postura.");
                        panelImagen.removeAll();
                        panelImagen.add(retornarImagenPostura("noHayPostura.gif"));
                    }else{
                        panelTraduccion.setText(postura.toString());
                        panelImagen.removeAll();
                        panelImagen.add(retornarImagenPostura(postura.getNombreImagen()+".gif"));
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
        posturaTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char caracter = e.getKeyChar();
                if(Character.isDigit(caracter)){
                    JOptionPane.showMessageDialog(null, "Por favor, no ingrese números.", "Advertencia", JOptionPane.WARNING_MESSAGE);
                    e.consume();
                }
            }
        });
        comboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                posturaTextField.setText("");
                panelTraduccion.setText("");
                panelImagen.removeAll();
                panelImagen.add(retornarImagenPostura("inicioPostura.gif"));

            }
        });
    }

    public void crearFrame() {
        setSize(680, 630);
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

    public JLabel retornarImagenPostura(String nombreImagen){
        ImageIcon imagen = new ImageIcon("src/main/java/ec/edu/epn/visual/imagenes/posturas/"+nombreImagen);
        Image scaledIcon = imagen.getImage().getScaledInstance(390, 340, Image.SCALE_DEFAULT);
        JLabel label = new JLabel(new ImageIcon(scaledIcon));
        return label;
    }

}
