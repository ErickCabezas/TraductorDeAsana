package ec.edu.epn.visual;

import ec.edu.epn.Diccionario;
import ec.edu.epn.PosturaAsana;

import javax.sound.sampled.LineUnavailableException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;
import java.io.InputStream;
import java.io.BufferedInputStream;


public class PantallaTraduccionPostura extends JFrame {
    private JPanel panelPrincipal;
    private JTextField posturaTextField;
    private JTextPane panelTraduccion;
    private JButton traducirButton;
    private JButton regresarButton;
    private JPanel panelImagen;
    private JComboBox comboBox;
    private JButton botonSonido;

    PosturaAsana postura = null;
    Clip clip;

    {
        try {
            clip = AudioSystem.getClip();
        } catch (LineUnavailableException e) {
            throw new RuntimeException(e);
        }
    }

    public PantallaTraduccionPostura() {
        this.colocarIconos("/iconos/iconoTraducir.png",traducirButton);
        this.colocarIconos("/iconos/iconoRegresar.png",regresarButton);
        this.colocarIconos("/iconos/iconoSonido.png", botonSonido);

        Diccionario diccionario = new Diccionario();
        panelImagen.add(retornarImagenPostura("inicioPostura.gif"));

        traducirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(clip.isRunning()){
                    clip.stop();
                    clip.close();
                }

                String posturaBuscada = posturaTextField.getText().trim();
                if(posturaBuscada.equalsIgnoreCase("")){
                    panelTraduccion.setText("");
                    JOptionPane.showMessageDialog(null, "Error #1\nNo ha ingresado ningúna postura.\nPor favor, escriba una.", "!Algo salió mal!", JOptionPane.ERROR_MESSAGE);
                    panelImagen.removeAll();
                    panelImagen.add(retornarImagenPostura("inicioPostura.gif"));
                }else{
                    int tipoTraduccion = comboBox.getSelectedIndex();
                    postura = null;
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
                pantallaBienvenida.crearFrame(getExtendedState());
                clip.stop();
                clip.close();
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
                if(clip.isRunning()){
                    clip.stop();
                    clip.close();
                }

            }
        });
        botonSonido.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(panelTraduccion.getText().equalsIgnoreCase("")||panelTraduccion.getText().equalsIgnoreCase("No hallamos esa postura.")){
                    if(!clip.isRunning()){
                        reproducirAudio("noHayPostura.wav");
                    }
                    JOptionPane.showMessageDialog(null, "La postura no es válida, o no has ingresado ninguna.\nPor favor, escriba una.", "Recuerda...", JOptionPane.WARNING_MESSAGE);
                }else{
                    if(!clip.isRunning()){
                        reproducirAudio(postura.getNombreImagen()+".wav");
                    }else{
                        clip.stop();
                        clip.close();
                    }
                }
            }
        });
    }

    public void crearFrame(int maximizada) {
        if(maximizada!=0){
            this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        }
        setSize(680, 660);
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

    public JLabel retornarImagenPostura(String nombreImagen){
        ImageIcon imagen = new ImageIcon(getClass().getResource("/posturas/"+nombreImagen));
        Image scaledIcon = imagen.getImage().getScaledInstance(390, 340, Image.SCALE_DEFAULT);
        JLabel label = new JLabel(new ImageIcon(scaledIcon));
        return label;
    }

    public void reproducirAudio(String audio){
        try {
            InputStream path = getClass().getResourceAsStream("/audio/"+audio);
            BufferedInputStream bufferedInputStream = new BufferedInputStream(path);
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(bufferedInputStream);
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();

        }catch (Exception e){
            System.out.println("Error al reproducir el sonido: " + e.getMessage());
        }
    }



}
