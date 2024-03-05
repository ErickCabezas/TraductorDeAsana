package ec.edu.epn.visual;

import ec.edu.epn.Diccionario;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AgregarPosturas extends JFrame{
    private JTextField txtSanscrit;
    private JTextField txtingles;
    private JTextField txtEsp;
    private JTextField txtPalabrasBase;
    private JButton btnGuardar;
    private JPanel panelAgregarPosturas;
    private JButton btnRegresar;
    private JPanel panelAyuda;

    public AgregarPosturas() {
        this.colocarIconos("/iconos/iconoGuardar.png",btnGuardar);
        this.colocarIconos("/iconos/iconoRegresar.png",btnRegresar);
        panelAyuda.add(this.retornarImagen("/robotAgregar.gif"));
        Diccionario diccionario = new Diccionario();
        btnGuardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(camposllenos()){
                    String palabraSanscrit=txtSanscrit.getText();
                    String palabraEspa=txtEsp.getText();
                    String palabraIngles=txtingles.getText();
                    String palabrasBase=txtPalabrasBase.getText();
                    if(diccionario.agregarPostura(palabraSanscrit,palabraIngles,palabraEspa,palabrasBase)){
                        JOptionPane.showMessageDialog(null,
                                "Postura agragada correctamente,\n" +
                                        "Debe reiniciar el programa para aplicar los cambios", "Notificación", JOptionPane.INFORMATION_MESSAGE);
                        limpiarPantalla();
                    }else{
                        JOptionPane.showMessageDialog(null,
                                "Error al agregar postura,\n"+
                                        "la postura pude ya existir en el diccionario",
                                "Notificación", JOptionPane.ERROR_MESSAGE);
                        limpiarPantalla();
                    }
                }else{
                    JOptionPane.showMessageDialog(null,
                            "Error al agregar postura,\n"+
                                    "llene todos los campos solicitados",
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

    public JLabel retornarImagen(String nombreImagen){
        ImageIcon imagen = new ImageIcon(getClass().getResource("/ventanas"+nombreImagen));
        Image scaledIcon = imagen.getImage().getScaledInstance(250, 200, Image.SCALE_DEFAULT);
        JLabel label = new JLabel(new ImageIcon(scaledIcon));
        return label;
    }

    public void colocarIconos(String direccion, JButton boton){
        ImageIcon iconoNuevo = new ImageIcon(getClass().getResource(direccion));
        Image scaledIcon = iconoNuevo.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        boton.setIcon(new ImageIcon(scaledIcon));
        boton.setPreferredSize(new Dimension(60, 60));
        boton.setIconTextGap(10);
    }

    public void limpiarPantalla(){
        txtEsp.setText(null);
        txtingles.setText(null);
        txtSanscrit.setText(null);
        txtPalabrasBase.setText(null);
    }

    public boolean camposllenos(){
        if (!txtEsp.getText().isEmpty() && !txtSanscrit.getText().isEmpty() && !txtingles.getText().isEmpty()
                && !txtPalabrasBase.getText().isEmpty()) {
            // Todos los campos están llenos
            return true;
        } else {
            // Al menos un campo está vacío
            return false;
        }
    }


}
