package ec.edu.epn.visual;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class PantallaInicial extends JFrame {
    private JPanel panelPrincipal;
    private JButton iniciarButton;
    private JButton acercaDelProgramaButton;
    private JButton sobreNosotrosButton;
    private JPanel panelCentral;


    public PantallaInicial() {
        this.colocarIconos("/iconos/iconoVentana.png",iniciarButton);
        this.colocarIconos("/iconos/iconoAcercaDe.png",acercaDelProgramaButton);
        panelCentral.add(this.retornarImagen("/robotInicio.gif"));

        iniciarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PantallaBienvenida pantallaBienvenida = new PantallaBienvenida();
                pantallaBienvenida.crearFrame(getExtendedState());
                dispose();
            }
        });
        acercaDelProgramaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null,
                        "Este programa está diseñado para\n"+
                                "ayudarte a traducir posturas de\n" +
                                "Sánscrito hacia Español e Ingles.\n" +
                                "Además, podrás conocer como está\n" +
                                "constituida a nivel de morfémas.\n" +
                                "Incluso, podrás consultar una en\n" +
                                "específico si lo prefieres. \n\n" +
                                "Deseamos que te sea de utilidad.\n" +
                                "atte. DarkChococrispis INC.", "Información del programa", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        sobreNosotrosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null,
                        "DarkChococrispis INC. es un gran grupo\n" +
                                "de amigos que se dedica a brindar a las\n" +
                                "personas Software de calidad por medio\n" +
                                "de buenas prácticas del campo. Este, está\n" +
                                "conformado por:\n" +
                                "- Alejandro Jiménez\n" +
                                "- Bryan Rosillo\n" +
                                "- Christian Hernández\n" +
                                "- Erick Cabezas\n" +
                                "- Jorge Segovia", "Sobre nosotros...", JOptionPane.INFORMATION_MESSAGE);
            }
        });
    }


    public void crearFrame(int maximizada) {
        if(maximizada!=0){
            this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        }
        setSize(600, 450);
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

    public JLabel retornarImagen(String nombreImagen){
        ImageIcon imagen = new ImageIcon(getClass().getResource("/ventanas"+nombreImagen));
        Image scaledIcon = imagen.getImage().getScaledInstance(250, 200, Image.SCALE_DEFAULT);
        JLabel label = new JLabel(new ImageIcon(scaledIcon));
        return label;
    }
}
