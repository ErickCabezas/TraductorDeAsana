package ec.edu.epn.visual;

import ec.edu.epn.Diccionario;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AgregarPosturas extends JFrame{
    private JPanel panelCentral;
    private JTextField txtSanscrit;
    private JTextField txtingles;
    private JTextField txtEsp;
    private JTextField txtPalabrasBase;
    private JButton btnGuardar;
    private JPanel panelAgregarPosturas;
    private JButton btnRegresar;

    public AgregarPosturas() {
        Diccionario diccionario = new Diccionario();
        btnGuardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String palabraSanscrit=txtSanscrit.getText();
                String palabraEspa=txtEsp.getText();
                String palabraIngles=txtingles.getText();
                String palabrasBase=txtPalabrasBase.getText();
                if(diccionario.agregarPostura(palabraSanscrit,palabraIngles,palabraEspa,palabrasBase)){
                    JOptionPane.showMessageDialog(null,
                            "Postura agragada correctamente", "Notificación", JOptionPane.INFORMATION_MESSAGE);
                }else{
                    JOptionPane.showMessageDialog(null,
                            "Error al agregar postura,\n"+
                                    "la postura pude ya existir en el diccionario",
                            "Notificación", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        btnRegresar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PantallaBienvenida pantallaBienvenida = new PantallaBienvenida();
                pantallaBienvenida.crearFrame(getExtendedState());
                dispose();
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
        add(panelAgregarPosturas);
        setVisible(true);
        Image image = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/iconos/iconoVentana.png"));
        setIconImage(image);
    }
}
