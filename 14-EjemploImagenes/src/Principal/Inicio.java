package Principal;

import java.awt.Image;
import java.awt.Toolkit;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Inicio {
    public static void main(String[] args){
        
        // -------------------------------------------------------------------------------------------------------
        // Cargando imagen desde archivo en el computador
        CargarImagen ventana = new CargarImagen();
        
        // -------------------------------------------------------------------------------------------------------
        // Cargango imagen desde URL - Solo admite png - jpg
        String urlStr = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/home/4.png";
        try {
            URL url = new URL(urlStr);
            Image imagen = Toolkit.getDefaultToolkit().createImage(url);

            if (imagen != null) {
                JFrame frame = new JFrame();
                frame.getContentPane().add(new JLabel(new ImageIcon(imagen)));
                frame.pack();
                frame.setVisible(true);
            } else {
                System.out.println("No se pudo cargar la imagen desde la URL proporcionada.");
            }
        } catch (Exception e) {
            System.out.println("Error al cargar la imagen: " + e.getMessage());
        }
        
    }
}
