package ec.edu.epn;


import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Imagen  extends JPanel {
    private ImageIcon imagen;
    private String nombreImagen;

    public Imagen(String nombreImagen) {
        this.nombreImagen = nombreImagen;
    }

    @Override
    public void paint(Graphics g) {
        imagen = new ImageIcon(getClass().getResource("src/main/java/ec/edu/epn/visual/imagenes/"+this.nombreImagen));
        g.drawImage(imagen.getImage(), 0, 0, getWidth(), getHeight(), this);
        setOpaque(false);
        super.paintComponent(g);

    }
}
