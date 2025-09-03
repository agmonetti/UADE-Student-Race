package vista;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Mapa {
    private BufferedImage imagenMapa;
    private Rectangle[] colisiones;
    private Rectangle puertaNivel;

    public Mapa(String rutaImagen) {
        try {
            imagenMapa = ImageIO.read(new File("src/resources/mapa.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Definir las áreas de colisión (muebles y paredes)
        colisiones = new Rectangle[]{
                new Rectangle(50, 50, 100, 30), // Ejemplo de mueble
                new Rectangle(200, 100, 150, 30), // Ejemplo de pared
                // Añadir más rectángulos según la disposición de los muebles y paredes en la imagen
        };

        // Definir la puerta de nivel
        puertaNivel = new Rectangle(400, 0, 50, 30); // Ajustar según la posición de la puerta en la imagen
    }

    public void dibujar(Graphics g) {
        g.drawImage(imagenMapa, 0, 0, null);
    }

    public boolean colisiona(Rectangle jugador) {
        for (Rectangle colision : colisiones) {
            if (jugador.intersects(colision)) {
                return true;
            }
        }
        return false;
    }

    public boolean esPuertaNivel(Rectangle jugador) {
        return jugador.intersects(puertaNivel);
    }
}
