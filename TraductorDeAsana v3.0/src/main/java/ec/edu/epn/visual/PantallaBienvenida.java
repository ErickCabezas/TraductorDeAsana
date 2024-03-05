package ec.edu.epn.visual;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class PantallaBienvenida extends JFrame {
    private JPanel panelPrincipal;
    private JButton traducirPosturaButton;
    private JButton traducirMorfemaButton;
    private JButton regresarButton;
    private JPanel panelCentral;
    private JButton agregarPosturaButton;

    public PantallaBienvenida() {
        this.colocarIconos("/iconos/iconoRegresar.png",regresarButton);
        this.colocarIconos("/iconos/iconoPostura.png",traducirPosturaButton);
        this.colocarIconos("/iconos/iconoMorfema.png",traducirMorfemaButton);
        this.colocarIconos("/iconos/iconoAgregar2.png",agregarPosturaButton);
        panelCentral.add(this.retornarImagen("/robotBienvenida.gif"));


        traducirPosturaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PantallaTraduccionPostura pantallaTraduccionPostura = new PantallaTraduccionPostura();
                pantallaTraduccionPostura.crearFrame(getExtendedState());
                dispose();
            }
        });
        agregarPosturaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AgregarPosturas agregarPosturas = new AgregarPosturas();
                agregarPosturas.crearFrame(getExtendedState());
                dispose();
            }
        });
        traducirMorfemaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PantallaTraduccionMorfema pantallaTraduccionMorfema = new PantallaTraduccionMorfema();
                pantallaTraduccionMorfema.crearFrame(getExtendedState());
                dispose();
            }
        });
        regresarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PantallaInicial pantallaInicial = new PantallaInicial();
                pantallaInicial.crearFrame(getExtendedState());
                dispose();
            }
        });
    }

    public void crearFrame(int maximizada) {
        if(maximizada!=0){
            this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        }
        setSize(800, 500);
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
        ImageIcon iconoNuevo = new ImageIcon(Objects.requireNonNull(getClass().getResource(direccion)));
        Image scaledIcon = iconoNuevo.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        boton.setIcon(new ImageIcon(scaledIcon));
        boton.setPreferredSize(new Dimension(50, 50));
        boton.setIconTextGap(10);
    }

    public JLabel retornarImagen(String nombreImagen){
        ImageIcon imagen = new ImageIcon(getClass().getResource("/ventanas"+nombreImagen));
        Image scaledIcon = imagen.getImage().getScaledInstance(250, 260, Image.SCALE_DEFAULT);
        JLabel label = new JLabel(new ImageIcon(scaledIcon));
        return label;
    }


}
