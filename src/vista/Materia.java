package vista;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Materia {
    private String nombre;
    private int posicionX;
    private int posicionY;
    private int ancho;
    private int alto;
    private int velocidad;
    private Image imagen;
    private static final int VELOCIDAD_MAXIMA = 18;
    private static final String[] imagenesLibro = {"libroAzul_2.png", "libroRojo_3.png", "libroVerde_4.png"};

    public Materia(String nombre, int posicionX, int posicionY, int velocidad) {
        this.nombre = nombre;
        this.posicionX = posicionX;
        this.posicionY = posicionY;
        this.ancho = 100;
        this.alto = 40;
        this.velocidad = Math.min(velocidad, VELOCIDAD_MAXIMA);

        String imagenes = imagenesLibro[new Random().nextInt(imagenesLibro.length)];
        this.imagen = new ImageIcon("src/resources/" + imagenes).getImage();
    }

    public void mover() {
        posicionX -= velocidad;
    }

    // si

    public void dibujar(Graphics2D g2d) {
        g2d.drawImage(imagen, posicionX, posicionY, ancho, alto, null);

        g2d.setColor(Color.GREEN);
        g2d.setFont(new Font("Arial", Font.BOLD, 12));
        FontMetrics fm = g2d.getFontMetrics();

        // Dividir el nombre de la materia en palabras
        String[] palabras = nombre.split(" ");
        int lineHeight = fm.getHeight();
        int y = posicionY + alto / 2 - (palabras.length * lineHeight) / 2;

        // Dibujar cada palabra en una línea separada
        for (String palabra : palabras) {
            int textWidth = fm.stringWidth(palabra);
            int x = posicionX + (ancho - textWidth) / 2;

            g2d.drawString(palabra, x, y);
            y += lineHeight;
        }
    }

    public boolean fueraDePantalla() {
        return posicionX + ancho < 0;
    }

    // ???????????????

    public int getPosicionX() {
        return posicionX;
    }

    public int getPosicionY() {
        return posicionY;
    }

    public int getAncho() {
        return ancho;
    }

    public int getAlto() {
        return alto;
    }
}