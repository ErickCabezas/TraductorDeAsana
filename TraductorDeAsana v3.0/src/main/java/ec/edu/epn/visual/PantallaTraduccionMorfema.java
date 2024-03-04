package ec.edu.epn.visual;

import ec.edu.epn.Diccionario;
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
    private JComboBox comboBox;


    public PantallaTraduccionMorfema() {
        this.colocarIconos("/iconos/iconoTraducir.png",traducirButton);
        this.colocarIconos("/iconos/iconoRegresar.png",regresarButton);
        Diccionario diccionario = new Diccionario();
        panelImagen.add(retornarImagenMorfema("holaMorfema.gif"));
        traducirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String morfemaBuscado = morfemaTextField.getText().trim();
                if(morfemaBuscado.equalsIgnoreCase("")){
                    panelTraduccion.setText("");
                    JOptionPane.showMessageDialog(null, "Error #2\nNo ha ingresado ningún morféma.\nPor favor, escriba una.", "!Algo salió mal!", JOptionPane.ERROR_MESSAGE);
                    panelImagen.removeAll();
                    panelImagen.add(retornarImagenMorfema("holaMorfema.gif"));
                }else{
                    int tipoTraduccion = comboBox.getSelectedIndex();
                    Morfema morfema = null;
                    if(tipoTraduccion == 0){
                        morfema = diccionario.buscarMorfemaSanscrito(morfemaBuscado);
                    }else if (tipoTraduccion == 1){
                        morfema = diccionario.buscarMorfemaEspaniol(morfemaBuscado);
                    } else if (tipoTraduccion == 2) {
                        morfema = diccionario.buscarMorfemaIngles(morfemaBuscado);
                    }
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
                pantallaBienvenida.crearFrame(getExtendedState());
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
        comboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                morfemaTextField.setText("");
                panelTraduccion.setText("");
                panelImagen.removeAll();
                panelImagen.add(retornarImagenMorfema("holaMorfema.gif"));
            }
        });
    }

    public void crearFrame(int maximizada) {
        if(maximizada!=0){
            this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        }
        setSize(490, 470);
        setTitle("Traductor");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setResizable(true);
        setLocationRelativeTo(null);
        add(panelPrincipal);
        setVisible(true);
        Image image = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/iconos/iconoVentana.png"));
        setIconImage(image);
    }

    public void colocarIconos(String direccion, JButton boton){
        ImageIcon iconoNuevo = new ImageIcon(getClass().getResource(direccion));
        Image scaledIcon = iconoNuevo.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        boton.setIcon(new ImageIcon(scaledIcon));
        boton.setPreferredSize(new Dimension(50, 50));
        boton.setIconTextGap(10);

    }

    public JLabel retornarImagenMorfema(String nombreImagen){
        ImageIcon imagen = new ImageIcon(getClass().getResource("/morfemas/"+nombreImagen));
        Image scaledIcon = imagen.getImage().getScaledInstance(260, 200, Image.SCALE_DEFAULT);
        JLabel label = new JLabel(new ImageIcon(scaledIcon));
        return label;
    }

}
